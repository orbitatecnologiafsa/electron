import Datepicker from '../../components/Datepicker';
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Sidebar from '../../partials/Sidebar';
import Header from '../../partials/Header';
import axios from 'axios';
import DropDown from '../../components/DropDown';

const VendedoresAdd = () => {
    
  const [docDigitado, setDocDigitado] = useState('');

  const [documentoValue, setDocumentoValue] = useState('');
  const [cepCnpj, setCepCnpj] = useState('');
  const [tipoDrop, setTipoDrop] = useState('');
  const [baseCal, setBaseCal] = useState('');
  const [cepUf, setUf] = useState('');

  const dropTipoComissao = [
    'Total da venda',
    'Parcela Recebida',
  ];

  const dropBaseCalculo = [
    'Total Líquido (Total + Impostos)',
    'Total Bruto (Preço * Quantidade)',
    'Total (Total Bruto - Descontos + Acréscimos)',
  ];

  const [formsData, setFormsData] = useState({
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
    bairro: '',
    município: '',
    uf: '',
    complemento: '',
    telefone: '',
    celular: '',
    email: '',
    nascimento: '',
    desconto: '',
    comissao:'',
    tipoComissao:'',
    baseCalculo:'',
    observacao: '',
  });
  
  const handleMenuItemClick = (item, tipo) => {
    if(tipo === 'Pessoa'){
        if(item === 'Fisica'){
            setCepCnpj('CPF');
        }else if(item === 'Juridica'){
            setCepCnpj('CNPJ');
        }
        formsData.cpf_cnpj = (item);
    }else if(tipo === 'TipoComissao'){
        formsData.tipoComissao = (item);
        setTipoDrop(item);
    }else if(tipo === 'UF'){
        formsData.uf = (item);
        setUf(item);
    }else if(tipo == "BaseCal"){
        if(item === 'Total Líquido (Total + Impostos)'){
            setBaseCal('Total Líquido');
        }else if(item === 'Total Bruto (Preço * Quantidade)'){
            setBaseCal('Total Bruto');
        }else if(item === 'Total (Total Bruto - Descontos + Acréscimos)'){
            setBaseCal('Total');
        }
        formsData.baseCalculo = (item);
    }
  };

  const handleDateChange = (dates) => {
    formsData.nascimento = dates[0].toLocaleDateString('pt-BR');
    console.log("Data selecionada:", formsData.nascimento);
  };

  const [error, setError] = useState(null);


  const [sidebarOpen, setSidebarOpen] = useState(false);
  {/* Post ADD Tabela */}
  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
        const response = await axios.post("http://localhost:8080/empresas-proprietarias/", formData);
        setFormsData({
            tipo: '',
            razaoSocial: '',
            nomeFantasia: '',
            cpfCnpj: '',
            cep: '',
            nomeExibicao: '',
            municipio: '',
            uf: '',
            bairro: '',
            logradouro: '',
            numero: '',
            complemento: '',
            email: '',
            telefone: '',
            celular: '',
            contato: '',
            rgInscricaoEstadual: '',
            inscricaoEstadualMunicipal: '',
            observacao: '',
            ativo: true,
        });
    } catch (error) {
        console.error('Erro ao cadastrar empresa:', error.response ? error.response.data : error.message);
    }
  };


  {/* Pegando dados da API ao digitar o CEP */}
  const getDadosEnderecoCEP = async (cepDigitado) => {
    try {
        const responseCEP = await axios.get(`https://viacep.com.br/ws/${cepDigitado}/json/`);
        if (responseCEP.data.erro) {
            throw new Error('CEP não encontrado.');
        }
        setFormData((prevData) => ({
            ...prevData,
            logradouro: responseCEP.data.logradouro,
            bairro: responseCEP.data.bairro,
            municipio: responseCEP.data.localidade,
            uf: responseCEP.data.uf
        }));
        setError(null);
    } catch (error) {
        setError('Erro ao buscar CEP: ' + error.message);
        setFormData((prevData) => ({
            ...prevData,
            logradouro: '',
            bairro: '',
            municipio: '',
            uf: ''
        }));
    }
  }; 

  {/* Redireciona para Empresas */}
  const handleRedirect = () => {
    navigate('/cadastro/vendedores');
  };

  const navigate = useNavigate();

  const handleInputChange = (e) => {
    const { name, value } = e.target;

    // Atualiza o estado normalmente
    setFormsData((prevData) => ({ ...prevData, [name]: value }));
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
            <form onSubmit={handleSubmit} className="space-y-5 mx-[2rem] max-w-full h-[80rem]">
              <h3 className="text-lg font-semibold justify-center text-center mb-4 mt-4 ml-1">Cadastro de Vendedores</h3>
              
              {/* Dados do Fornecedor */}
              <h2 style={{ color: '#5E16ED', fontSize: '170%', fontWeight: 'bold' }}>
                Dados
                <hr style={{ border: '1px solid #5E16ED' }} />
              </h2>

              <div className="flex flex-col md:flex-row gap-4 justify-between">
                <div className="flex flex-col">
                <label className="block ml-1 text-sm font-medium leading-6 text-black">{ cepCnpj ? cepCnpj : 'CPF'}</label>
                  <input
                    type="text"
                    name="cpf/cnpj"
                    value={formsData.codigo}
                    onChange={handleInputChange}
                    className=" w-[20rem] h-11 px-3 py-2 rounded-md  ring-inset focus:ring-2 focus:ring-indigo-600"
                    required
                  />
                </div>

                <div className="flex flex-col mr-4">
                    <DropDown labelDrop={"Tipo de Fornecedor"} title= 'Selecione o tipo' ValorBtn={formsData.cpf_cnpj} listItens={['Fisica', 'Juridica']} onSelect={(item) => handleMenuItemClick(item, "Pessoa")} />

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
                    name="nome/RazaoSocial"
                    value={formsData.nome_razao}
                    onChange={handleInputChange}
                    className=" w-[23rem] h-11 px-3 py-2 rounded-md  ring-inset focus:ring-2 focus:ring-indigo-600"
                  />
                </div>
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Fantasia</label>
                  <input
                    type="text"
                    name="grupo"
                    value={formsData.fantasia}
                    onChange={handleInputChange}
                    className=" w-[23rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600"
                  />
                </div>
                <div className="flex flex-col">
                    <label className="block ml-1 text-sm font-medium leading-6 text-black">Data de nascimento</label>
                    <Datepicker align="center" onDateChange={handleDateChange}/>
                </div>
              </div>

              {/* Aréa da Registro*/}
              <div className="flex flex-col md:flex-row gap-4">
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">RG- Registro Geral</label>
                  <input
                    type="text"
                    name="inscricao_estadual_municipal"
                    value={formsData.inscricao_estadual_municipal}
                    onChange={handleInputChange}
                    className="w-[32.5rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                  />
                </div>
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
                    value={formsData.cepCnpj}
                    onChange={handleInputChange}
                    className="w-[25rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                  />
                </div>
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Logradouro</label>
                  <input
                    type="text"
                    name="logradouro"
                    value={formsData.logradouro}
                    onChange={handleInputChange}
                    className="w-[25rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                  />
                </div>
                <div className="flex flex-col">
                    <DropDown labelDrop="UF" title= 'Selecione a UF' ValorBtn={cepUf} listItens={["US", "MX", "BA"]} onSelect={(item) => handleMenuItemClick(item,'UF')} />
                </div>
              </div>

              <div className="flex flex-col md:flex-row gap-4 justify-between">

                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Numero</label>
                  <input
                    type="text"
                    name="numero"
                    value={formsData.numero}
                    onChange={handleInputChange}
                    className="w-[32.5rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                  />
                </div>

                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Municipio</label>
                  <input
                    type="text"
                    name="municipio"
                    value={formsData.município}
                    onChange={handleInputChange}
                    className="w-[32.5rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
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
                    className="w-[32.5rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                  />
                </div>
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
              </div>

              {/*Dados do veículo*/}
              <h2 style={{ color: '#5E16ED', fontSize: '170%', fontWeight: 'bold' }}>
                Valores
                <hr style={{ border: '1px solid #5E16ED' }} />
              </h2>

              {/* Aréa do veículo*/}
              <div className="flex flex-col md:flex-row gap-4 justify-between ">
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Desconto(%)</label>
                  <input
                    type="text"
                    name="Placa"
                    value={formsData.desconto}
                    onChange={handleInputChange}
                    className="w-[15rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                  />
                </div>
                
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Comissão(%)</label>
                  <input
                    type="text"
                    name="antt"
                    value={formsData.antt}
                    onChange={handleInputChange}
                    className="w-[15rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                  />
                </div>

                <div className="flex flex-col ">
                    <DropDown labelDrop="Tipo de Comissão" title='Selecione o tipo de comissão' ValorBtn={tipoDrop} listItens={dropTipoComissao} onSelect={(item) => handleMenuItemClick(item,'TipoComissao')}/>
                </div>

                <div className="flex flex-col ">
                <DropDown labelDrop="Base de Calculo" title="Selecione a base de calculo" ValorBtn={baseCal} listItens={dropBaseCalculo} onSelect={(item) => handleMenuItemClick(item,'BaseCal')}/>
                </div>
              </div>

              {/*Observação*/}
              <div className="flex flex-col md:flex-row gap-4">
              <div className="flex flex-col">
                <label className="block ml-1 text-sm font-medium leading-6 text-black">Observação</label>
                <textarea
                        type="text"
                        name="descricao"
                        value={formsData.observacao}
                        onChange={handleInputChange}
                        className="w-[66rem] h-[45px] resize-none px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600"
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

export default VendedoresAdd;