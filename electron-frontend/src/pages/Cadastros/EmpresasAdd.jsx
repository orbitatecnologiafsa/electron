import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Sidebar from '../../partials/Sidebar';
import Header from '../../partials/Header';
import axios from 'axios';
import DropDown from '../../components/DropDown';

function EmpresasAdd() {

  const [docDigitado, setDocDigitado] = useState('');

  const [documentoValue, setDocumentoValue] = useState('');
  const [isDocumentoSelected, setIsDocumentoSelected] = useState(false);
  const [tipoCliente, setTipoCliente] = useState('-');
  const [cep, setCep] = useState('');
  const [cnpj, setCnpj] = useState('');
  const [dropEstado, setDropEstado] = useState('');

  const [error, setError] = useState(null);

  const handleCepChange = (e) => {
    const value = e.target.value.replace(/\D/g, '');
    const formattedValue = value.replace(/(\d{5})(\d{3})/, '$1-$2');
  
    setCep(formattedValue);
    
    if (formattedValue.length === 9) {
      getDadosEnderecoCEP(value);
    }
  };


  const [sidebarOpen, setSidebarOpen] = useState(false);
  {/* Post ADD Tabela */}
  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
        const response = await axios.post("http://localhost:8080/empresas-proprietarias", formData);
        setFormData({
            tipo: '',
            razaoSocial: '',
            nomeFantasia: '',
            cpfCnpj: '',
            cep: '',
            nomeExibicao: '',
            municipio: '',
            estado: '',
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
            observacoes: '',
            ativo: true,
        });
    } catch (error) {
        console.error('Erro ao cadastrar empresa:', error.response ? error.response.data : error.message);
    }
  };

  const [formData, setFormData] = useState({
    razaoSocial: '',
    nomeFantasia: '',
    cpfCnpj: '',
    cep: '',
    municipio: '',
    estado: '',
    bairro: '',
    logradouro: '',
    numero: '',
    complemento: '',
    pfOuPj: '',
    email: '',
    telefone: '',
    celular: '',
    contato: '',
    rgInscricaoEstadual: '',
    inscricaoEstadualMunicipal: '',
    observacoes: '',
    tipoPessoa: '',
    ativo: true,
    revenda: false,
  });

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
            estado: responseCEP.data.uf
        }));
        setError(null);
    } catch (error) {
        setError('Erro ao buscar CEP: ' + error.message);
        setFormData((prevData) => ({
            ...prevData,
            logradouro: '',
            bairro: '',
            municipio: '',
            estado: ''
        }));
    }
  };

  {/* Pegando dados da API ao digitar o CNPJ */}
  const getDadosCNPJ = async (cnpjDigitado) => {
    try {
        const responseCNPJ = await axios.get(`https://publica.cnpj.ws/cnpj/${cnpjDigitado}`);

        if (responseCNPJ.data.erro) {
            throw new Error('CNPJ não encontrado.');
        }

        setFormData((prevData) => ({
            ...prevData,
            nomeFantasia: responseCNPJ.data.estabelecimento?.nome_fantasia || '',
            nomeRazao: responseCNPJ.data.razao_social || '',
        }));
        console.log(responseCNPJ.data.estabelecimento?.cep);
        setError(null);
    } catch (error) {
        setError('Erro ao buscar CNPJ: ' + error.message);
        setFormData((prevData) => ({
            ...prevData,
            nomeFantasia: '',
            nomeRazao: '',
        }));
    }
  };

  const formatarCPF = (cpf) => {
    return cpf
      .replace(/\D/g, '')
      .replace(/(\d{3})(\d)/, '$1.$2')
      .replace(/(\d{3})(\d)/, '$1.$2')
      .replace(/(\d{3})(\d{1,2})$/, '$1-$2');
  };

  const formatarCNPJ = (cnpj) => {
    return cnpj
      .replace(/\D/g, '')
      .replace(/(\d{2})(\d)/, '$1.$2')
      .replace(/(\d{3})(\d)/, '$1.$2')
      .replace(/(\d{3})(\d)/, '$1/$2')
      .replace(/(\d{4})(\d{1,2})$/, '$1-$2');
  };

  {/* Redireciona para Empresas */}
  const handleRedirect = () => {
    navigate('/cadastro/empresas');
  };
  const navigate = useNavigate();

  const handleMenuItemClick = (item, tipo) => {
    if(tipo === 'Estado'){
        setDropEstado(item);
    }
    handleInputChange({ target: { name: 'estado', value: item } });
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
  
    // Verifica qual campo está sendo atualizado
    if (name === "complemento") {
      // Atualiza o complemento
      setFormData((prevData) => ({
        ...prevData,
        complemento: value,
      }));
      return;
    }
  
    if (name === "numero") {
      // Atualiza o número
      setFormData((prevData) => ({
        ...prevData,
        numero: value,
      }));
      return;
    }
  
    if (name === "cpfCnpj") {
      // Aqui tratamos os campos de CPF e CNPJ
      const numericValue = value.replace(/\D/g, '');
      let formattedValue = '';
      let maxLength = 11;
  
      if (numericValue.length <= 11) {
        formattedValue = formatarCPF(numericValue);
        setDocumentoValue('CPF');
        setFormData((prevData) => ({
          ...prevData,
          tipoPessoa: 'PESSOA_FISICA', // Pessoa Física
        }));
        maxLength = 11;
      } else if (numericValue.length <= 14) {
        formattedValue = formatarCNPJ(numericValue);
        setDocumentoValue('CNPJ');
        setFormData((prevData) => ({
          ...prevData,
          tipoPessoa: 'PESSOA_JURIDICA', // Pessoa Jurídica
        }));
        maxLength = 14;
        getDadosCNPJ(numericValue); // Chama a API para obter dados do CNPJ
      }
  
      const finalValue = numericValue.slice(0, maxLength);
      setDocDigitado(formattedValue);
  
      // Atualiza o valor do CPF/CNPJ no formData
      setFormData((prevData) => ({
        ...prevData,
        cpfCnpj: finalValue,
      }));
      return;
    }
    
    // Aqui é o fallback para qualquer outro campo não tratado
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
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
            <form onSubmit={handleSubmit} className="space-y-5 mx-[2rem] max-w-full h-[120vh]">
              <h3 className="text-lg font-semibold justify-center text-center mb-4 mt-4 ml-1">Cadastro de Empresa</h3>

              <div className="flex justify-between">
              <div className="flex gap-4 mt-2 mr-3">
                <div className="flex items-center">
                  <label className="text-base">Tipo de documento:</label>
                </div>
                <div className="flex items-center">
                  <label className="text-base">{documentoValue || '-'}</label> {/* Exibe CPF ou CNPJ */}
                </div>
              </div>

                {/* Ativo e Revenda */}
                <div className="flex gap-4 mt-2 mr-3">
                  <div className="flex items-center">
                    <input
                      type="checkbox"
                      name="ativo"
                      checked={formData.ativo}
                      onChange={() => setFormData({ ...formData, ativo: !formData.ativo })}
                      className="mr-2 rounded"
                    />
                    <label className="text-base">Ativo</label>
                  </div>
                  <div className="flex items-center">
                    <input
                      type="checkbox"
                      name="revenda"
                      checked={formData.revenda}
                      onChange={() => setFormData({ ...formData, revenda: !formData.revenda })}
                      className="mr-2 rounded"
                    />
                    <label className="text-base">Revenda</label>
                  </div>
                </div>
              </div>

              {/* Dados da Empresa */}

              <h2 style={{ color: '#5E16ED', fontSize: '170%', fontWeight: 'bold' }}>
                Dados
                <hr style={{ border: '1px solid #5E16ED' }} />
              </h2>

              <div className="flex flex-col md:flex-row gap-4">
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Razão Social</label>
                  <input
                    type="text"
                    name="nomeRazao"
                    value={formData.nomeRazao}
                    onChange={handleInputChange}
                    className=" w-[50rem] h-11 px-3 py-2 rounded-md ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-indigo-600"
                    required
                  />
                </div>
                <div className="flex flex-col">
                <label className="block ml-1 text-sm font-medium leading-6 text-black">Documento</label>
                  <input
                    type="text"
                    name="cpfCnpj"
                    value={docDigitado}
                    onChange={handleInputChange}
                    maxLength={18}
                    className="w-[15rem] h-11 px-3 py-2 rounded-md ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-indigo-600"
                  />
                </div>
              </div>

              <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Nome Fantasia</label>
                  <input
                    type="text"
                    name="nomeFantasia"
                    value={formData.nomeFantasia}
                    onChange={handleInputChange}
                    disabled={documentoValue === 'CPF'}
                    className="w-[1063px] h-11 px-3 py-2 rounded-md ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                  />
                </div>

                <div className="flex flex-col md:flex-row gap-4">
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">RG Inscrição Estadual</label>
                  <input
                    type="text"
                    name="rgInscricaoEstadual"
                    value={formData.rgInscricaoEstadual}
                    onChange={handleInputChange}
                    className="w-[33rem] h-11 px-3 py-2 rounded-md ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-indigo-600"
                  />
                </div>
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Inscrição Estadual Municipal</label>
                  <input
                    type="text"
                    name="inscricaoEstadualMunicipal"
                    value={formData.inscricaoEstadualMunicipal}
                    onChange={handleInputChange}
                    className="w-[32rem] h-11 px-3 py-2 rounded-md ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-indigo-600"
                  />
                </div>
              </div>

                {/* Contato do Cliente */}
                <h2 style={{ color: '#5E16ED', fontSize: '170%', fontWeight: 'bold' }}>
                  Contato
                  <hr style={{ border: '1px solid #5E16ED' }} />
                </h2>
                <div className="flex">
                  <div className="grid grid-cols-1 gap-4">
                    <div className="flex flex-col md:flex-row gap-4 max-w-[30rem]">
                      <div className="flex flex-col">
                        <label className="block ml-1 text-sm font-medium leading-6 text-black">Email</label>
                        <input
                          type="email"
                          name="email"
                          value={formData.email}
                          onChange={handleInputChange}
                          className="w-[22rem] h-11 px-3 py-2 rounded-md ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-indigo-600"
                        />
                      </div>
                      <div className="flex flex-col">
                        <label className="block ml-1 text-sm font-medium leading-6 text-black">Contato</label>
                        <input
                          type="text"
                          name="contato"
                          value={formData.contato}
                          onChange={handleInputChange}
                          className="w-[150px] h-11 px-3 py-2 rounded-md ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-indigo-600"
                        />
                      </div>
                    </div>
                    <div className="flex flex-col md:flex-row gap-4 max-w-[30rem]">
                      <div>
                          <label className="block ml-1 text-sm font-medium leading-6 text-black">Telefone</label>
                          <input
                            type="text"
                            name="telefone"
                            value={formData.telefone}
                            onChange={handleInputChange}
                            className="w-[16rem] h-11 px-3 py-2 rounded-md ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-indigo-600"
                          />
                        </div>
                        <div>
                          <label className="block ml-1 text-sm font-medium leading-6 text-black">Celular</label>
                          <input
                            type="text"
                            name="celular"
                            value={formData.celular}
                            onChange={handleInputChange}
                            className="w-[245px] h-11 px-3 py-2 rounded-md ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-indigo-600"
                          />
                        </div>
                      </div>
                    </div>
                    <div className="flex flex-col max-w-fit relative left-[72px]">
                    <label className="block ml-1 text-sm font-medium leading-6 text-black">Observação</label>
                      <textarea
                        type="text"
                        name="observacoes"
                        value={formData.observacoes}
                        onChange={handleInputChange}
                        className="w-[32rem] h-[136px] resize-none px-3 py-2 rounded-md ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-indigo-600"
                      />
                    </div>
                  </div>
                                {/* Endereço do Cliente */}
              <h2 style={{ color: '#5E16ED', fontSize: '170%', fontWeight: 'bold' }}>
                Endereço
                <hr style={{ border: '1px solid #5E16ED' }} />
              </h2>
              <div className="flex flex-col md:flex-row gap-4">
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">CEP</label>
                  <input
                    type="text"
                    name="cep"
                    value={cep}
                    onChange={handleCepChange}
                    className="w-[32.5rem] h-11 px-3 py-2 rounded-md ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-indigo-600"
                  />
                </div>
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Logradouro</label>
                  <input
                    type="text"
                    name="logradouro"
                    value={formData.logradouro}
                    onChange={handleInputChange}
                    readOnly
                    className="w-[32.5rem] h-11 px-3 py-2 rounded-md ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-indigo-600"
                  />
                </div>
              </div>

              <div className="flex flex-col md:flex-row gap-4 justify-between ">
              <div className="flex flex-col">
                <label className="block ml-1 text-sm font-medium leading-6 text-black">Número</label>
                <input
                  type="text"
                  name="numero"
                  value={formData.numero}
                  onChange={handleInputChange}
                  className="w-[7rem] h-11 px-3 py-2 rounded-md ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-indigo-600"
                />
              </div>

                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Bairro</label>
                  <input
                    type="text"
                    name="bairro"
                    value={formData.bairro}
                    onChange={handleInputChange}
                    readOnly
                    className="w-[26rem] h-11 px-3 py-2 rounded-md ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-indigo-600"
                  />
                </div>
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Município</label>
                  <input
                    type="text"
                    name="municipio"
                    value={formData.municipio}
                    onChange={handleInputChange}
                    readOnly
                    className="w-[11rem] h-11 px-3 py-2 rounded-md ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-indigo-600"
                  />
                </div>

                <div className="flex flex-col">
                  <DropDown name='estado' value={formData.estado} labelDrop="Estado" title= 'Selecione a UF' ValorBtn={dropEstado} listItens={["US", "MX", "BA"]} onSelect={(item) => handleMenuItemClick(item,'Estado')} />
                </div>
              </div>

              <div className="flex flex-col md:flex-row gap-4">
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Complemento</label>
                  <input
                    type="text"
                    name="complemento"
                    value={formData.complemento}
                    onChange={handleInputChange}
                    className="w-[1063px] h-11 px-3 py-2 rounded-md ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-indigo-600"
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

export default EmpresasAdd;