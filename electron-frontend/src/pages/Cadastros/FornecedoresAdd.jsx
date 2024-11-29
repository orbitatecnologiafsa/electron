import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Sidebar from '../../partials/Sidebar';
import Header from '../../partials/Header';
import axios from 'axios';
import DropDown from '../../components/DropDown';

function FornecesoresAdd() {

  const [docDigitado, setDocDigitado] = useState('');

  const [documentoValue, setDocumentoValue] = useState('');
  const [isDocumentoSelected, setIsDocumentoSelected] = useState(false);
  const [tipoCliente, setTipoCliente] = useState('-');
  const [uf, setUf] = useState('');
  const [cepCnpj, setCepCnpj] = useState('');
  const [valueCnpj, setValueCnpj] = useState('');

  const [formsData, setFormsData] = useState({
    index: "",
    cpf_cnpj: '',
    ativo: true,
    nome_razao: '',
    fantasia: '',
    rg_inscricao_estadual: '',
    inscricao_estadual_municipal: '',
    contato: '',
    cep: '',
    logradouro: '',
    numero: '',
    uf: '',
    município: '',
    bairro: '',
    complemento: '',
    celular: '',
    telefone: '',
    email: '',
    observacao: '',
  });


  const handleMenuItemClick = (item) => {
    if(item === 'Fisica'){
        setCepCnpj('CPF');
        formsData.cpf_cnpj = (item);
    }else if(item === 'Juridica'){
        setCepCnpj('CNPJ');
        formsData.cpf_cnpj = (item);
    }else if(item === 'UF'){
        formsData.uf = (item);
        setUf(item);
    }
  };

  const [error, setError] = useState(null);

  const [sidebarOpen, setSidebarOpen] = useState(false);
  {/* Post ADD Tabela */}
  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
        const response = await axios.post("http://localhost:8080/empresas-proprietarias", formData);
        setFormsData({
            cpf_cnpj: '',
            ativo: true,
            nome_razao: '',
            fantasia: '',
            rg_inscricao_estadual: '',
            inscricao_estadual_municipal: '',
            contato: '',
            cep: '',
            logradouro: '',
            numero: '',
            uf: '',
            município: '',
            bairro: '',
            complemento: '',
            celular: '',
            telefone: '',
            email: '',
            observacao: '',
        });
    } catch (error) {
        console.error('Erro ao cadastrar empresa:', error.response ? error.response.data : error.message);
    }
  };

  {/* Redireciona para Empresas */}
  const handleRedirect = () => {
    navigate('/cadastro/fornecedores');
  };
  const navigate = useNavigate();

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormsData((prevData) => ({ ...prevData, [name]: value }));
    setValueCnpj(value);
  };

  const getCNPJ = async (value) => {
    try {
      const responseCnpj = await axios.get('https://open.cnpja.com/office/' + value);
      
      // Atualiza os dados no estado
      setFormsData((prevData) => ({
        ...prevData,
        nome_razao: responseCnpj.data.company.name,
        fantasia: responseCnpj.data.alias,
        cep: responseCnpj.data.address.zip,
        cpf_cnpj: value,
        numero: responseCnpj.data.address.number,
        email: responseCnpj.data.emails[0]?.address || '',
        telefone: responseCnpj.data.phones[0]?.area + responseCnpj.data.phones[0]?.number || '',
        bairro: responseCnpj.data.address.district,
        logradouro: responseCnpj.data.address.street,
      }));
    } catch (error) {
      console.error('Erro ao buscar dados do CNPJ:', error);
    }
  };

  const handleCnpjBlur = () => {
    if (cepCnpj =='CNPJ') {
      getCNPJ(valueCnpj);
    }
  };

  {/* Tela principal do administrador */}
  return (
    <div className="flex h-screen overflow-hidden">

      {/* Sidebar */}
      <Sidebar sidebarOpen={sidebarOpen} setSidebarOpen={setSidebarOpen} />

        {/* Conteúdo */}
        <div className="relative flex flex-col flex-1 overflow-y-auto overflow-x-hidden">
          <Header sidebarOpen={sidebarOpen} setSidebarOpen={setSidebarOpen} />
          <div className="flex justify-center w-full">
            <form onSubmit={handleSubmit} className="space-y-5 mx-[2rem] max-w-full h-[70rem]">
              <h3 className="text-lg font-semibold justify-center text-center mb-4 mt-4 ml-1">Cadastro de Fornecedor</h3>
              
              {/* Dados do Fornecedor */}
              <h2 style={{ color: '#5E16ED', fontSize: '170%', fontWeight: 'bold' }}>
                Dados
                <hr style={{ border: '1px solid #5E16ED' }} />
              </h2>

              <div className="flex flex-col md:flex-row gap-4 justify-between">
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Tipo de Fornecedor</label>
                   <DropDown title={"Tipo de Fornecedor"} ValorBtn={cepCnpj} listItens={["Fisica", "Juridica"]} onSelect={(item) => handleMenuItemClick(item)}/>
                </div>
                <div className="flex flex-col">
                <label className="block ml-1 text-sm font-medium leading-6 text-black">{ cepCnpj ? cepCnpj : 'CPF'}</label>
                  <input
                    type="text"
                    name="cpf/cnpj"
                    value={formsData.codigo}
                    onChange={handleInputChange}
                    className=" w-[20rem] h-11 px-3 py-2 rounded-md  ring-inset focus:ring-2 focus:ring-indigo-600"
                    required
                    onBlur={handleCnpjBlur}
                  />
                </div>
                <div className="flex items-center">
                    <input
                      type="checkbox"
                      name="ativo"
                      checked={formsData.ativo}
                      onChange={() => formsData.ativo ? setFormsData({ ...formsData, ativo: false }) : setFormsData({ ...formsData, ativo: true })}
                      className="mr-2 rounded"
                    />
                    <label className="text-base">Ativo</label>
                </div>
              </div>

              {/*Nome e fantasia */}

              <div className="flex flex-col md:flex-row gap-4">
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Nome/Razão Social</label>
                  <input
                    type="text"
                    name="nome_razao"
                    value={formsData.nome_razao}
                    onChange={handleInputChange}
                    className=" w-[32.5rem] h-11 px-3 py-2 rounded-md  ring-inset focus:ring-2 focus:ring-indigo-600"
                    required
                  />
                </div>
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Fantasia</label>
                  <input
                    type="text"
                    name="fantasia"
                    value={formsData.fantasia}
                    onChange={handleInputChange}
                    className=" w-[32.5rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600"
                    required
                  />
                </div>
              </div>

              <h2 style={{ color: '#5E16ED', fontSize: '170%', fontWeight: 'bold' }}>
                Contato
                <hr style={{ border: '1px solid #5E16ED' }} />
              </h2>

              {/* Aréa do Contato*/}
              <div className="flex flex-col md:flex-row gap-4">
              <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Contato</label>
                  <input
                    type="text"
                    name="contato"
                    value={formsData.contato}
                    onChange={handleInputChange}
                    className="w-[20rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                  />
                </div>
              <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Celular</label>
                  <input
                    type="text"
                    name="celular"
                    value={formsData.celular}
                    onChange={handleInputChange}
                    className="w-[22rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                  />
                </div>
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Telefone</label>
                  <input
                    type="text"
                    name="telefone"
                    value={formsData.telefone}
                    onChange={handleInputChange}
                    className="w-[22rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                  />
                </div>
              </div>

              <div className="flex flex-col md:flex-row gap-4">
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Email</label>
                  <input
                    type="text"
                    name="email"
                    value={formsData.email}
                    onChange={handleInputChange}
                    className="w-[66rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                  />
                </div>
              </div>

              {/* Aréa da Registro*/}
              <div className="flex flex-col md:flex-row gap-4">
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">IE - Inscrição Estadual</label>
                  <input
                    type="text"
                    name="rg_inscricao_estadual"
                    value={formsData.rgInscricaoEstadual}
                    onChange={handleInputChange}
                    maxLength={documentoValue === 'CPF' ? 14 : 18}
                    className="w-[32.5rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                  />
                </div>
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">IEM - Inscricão Estadual Municipais</label>
                  <input
                    type="text"
                    name="inscricao_estadual_municipal"
                    value={formsData.inscricao_estadual_municipal}
                    onChange={handleInputChange}
                    className="w-[32.5rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                  />
                </div>
              </div>

              <h2 style={{ color: '#5E16ED', fontSize: '170%', fontWeight: 'bold' }}>
                Endereço
                <hr style={{ border: '1px solid #5E16ED' }} />
              </h2>

              {/* Aréa do endereço*/}
              <div className="flex flex-col md:flex-row gap-4">
                
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">CEP</label>
                  <input
                    type="text"
                    name="cep"
                    value={formsData.cep}
                    onChange={handleInputChange}
                    className="w-[21.3rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                  />
                </div>
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Logradouro</label>
                  <input
                    type="text"
                    name="logradouro"
                    value={formsData.logradouro}
                    onChange={handleInputChange}
                    className="w-[21.3rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                  />
                </div>
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Numero</label>
                  <input
                    type="text"
                    name="numero"
                    value={formsData.numero}
                    onChange={handleInputChange}
                    className="w-[21.3rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                  />
                </div>
              </div>

              <div className="flex flex-col md:flex-row gap-4">

                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Bairro</label>
                  <input
                    type="text"
                    name="bairro"
                    value={formsData.bairro}
                    onChange={handleInputChange}
                    className="w-[40.5rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                  />
                </div>

                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Municipio</label>
                  <input
                    type="text"
                    name="município"
                    value={formsData.município}
                    onChange={handleInputChange}
                    className="w-[20.5rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                  />
                </div>
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">UF</label>
                  <input
                    type="text"
                    name="uf"
                    value={formsData.uf}
                    onChange={handleInputChange}
                    className="w-[3rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                  />
                </div>
              </div>

              <div className="flex flex-col md:flex-row gap-4">
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Complemento</label>
                  <input
                    type="text"
                    name="complemento"
                    value={formsData.complemento}
                    onChange={handleInputChange}
                    className="w-[32.5rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                  />
                </div>
                <div className="flex flex-col">
                <label className="block ml-1 text-sm font-medium leading-6 text-black">Observação</label>
                <textarea
                        type="text"
                        name="observacao"
                        value={formsData.observacao}
                        onChange={handleInputChange}
                        className="w-[33rem] h-[45px] resize-none px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600"
                    />
              </div>
              </div>

              <div className="flex justify-end gap-4">
                <button type="submit" className="h-[3rem] w-40 px-4 py-2 rounded-md bg-indigo-600 text-white hover:bg-indigo-700">
                  Cadastrar
                </button>
                <button type="button" className="h-[3rem] w-[10rem] rounded-mdbg-white px-4 py-2 text-sm font-semibold text-gray-900 shadow-sm rounded-md ring-1 ring-inset ring-gray-300 hover:bg-gray-50">
                  Copiar
                </button>
                <button type="button" onClick={handleRedirect} className="h-[3rem] w-[10rem] rounded-mdbg-white px-4 py-2 text-sm font-semibold text-gray-900 shadow-sm rounded-md ring-1 ring-inset ring-gray-300 hover:bg-gray-50">
                  Voltar
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
    );
  }

export default FornecesoresAdd;