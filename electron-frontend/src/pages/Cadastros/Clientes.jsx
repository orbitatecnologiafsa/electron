import React, { useEffect, useState } from 'react';
import Sidebar from '../../partials/Sidebar';
import Header from '../../partials/Header';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUser, faSort, faCircleInfo, faMagnifyingGlass, faPenToSquare, faImage } from '@fortawesome/free-solid-svg-icons';
import axios from 'axios';
import { Menu, MenuButton, MenuItems, MenuItem } from '@headlessui/react';

function Clientes() {
  {/* Const para uso da API de CEP */}
  const [cep, setCep] = useState('');
  const [logradouro, setLogradouro] = useState('');
  const [bairro, setBairro] = useState('');
  const [localidade, setLocalidade] = useState('');
  const [uf, setUf] = useState('');
  const [error, setError] = useState(null);

  {/* Const para uso da API de CNPJ */}
  const [documentType, setDocumentType] = useState('');
  const [docDigitado, setDoc] = useState('');
  const [cpf, setCpf] = useState('');
  const [cnpj, setCnpj] = useState('');

  const [sidebarOpen, setSidebarOpen] = useState(false);
  const [campoValue, setCampoValue] = useState('Selecione um Campo');
  const [documentoValue, setDocumentoValue] = useState(null);;
  const [isDocumentoSelected, setIsDocumentoSelected] = useState(false);
  const [tipoCliente, setTipoCliente] = useState('-');

  {/* Upload de Imagem */}
  const [selectedImage, setSelectedImage] = useState(null);

  {/* Const de modal */}
  const [isModalLegendaOpen, setIsModalLegendaOpen] = useState(false);
  const [isModalCadastroOpen, setIsModalCadastroOpen] = useState(false);

  {/* Const API Clientes */}
  const [posts, setPosts] = useState([]);
  const [sortColumn, setSortColumn] = useState('id'); 

  {/* Sort da Tabela */}
  const [sortDirection, setSortDirection] = useState('asc'); 
  const [formData, setFormData] = useState({
      nomeRazao: '',
      fantasia: '',
      documento: '',
      cep: '',
      municipio: '',
      uf: '',
      bairro: '',
      logradouro: '',
      numero: '',
      complemento: '',
      cpfCnpj: '',
      pfOuPj: '',
      email: '',
      telefone: '',
      celular: '',
      contato: '',
      rgInscricaoEstadual: '',
      inscricaoEstadualMunicipal: '',
      observacao: '',
      ativo: true,
      revenda: false,
    });

  const handleMenuItemClick = (value) => {
    setCampoValue(value);
  };

  const handleDocumentoItemClick = (item) => {
    setDocumentoValue(item);
    const tipoCliente = item === 'CPF' ? 'PF' : 'PJ';
    setTipoCliente(tipoCliente);
    setFormData((prevData) => ({
        ...prevData,
        documento: '',
        cpfCnpj: '',
        pfOuPj: tipoCliente,
    }));
    setIsDocumentoSelected(true);
  };

  const handleDocumentChange = (e) => {
    const value = e.target.value;
    const isCpf = value.replace(/\D/g, '').length === 11;
    const isCnpj = value.replace(/\D/g, '').length === 14;

    if (documentoValue === 'CPF' && isCpf) {
        handleCpfChange(e);
    } else if (documentoValue === 'CNPJ' && isCnpj) {
        handleCnpjChange(e);
    } else {
        setFormData((prevData) => ({
            ...prevData,
            cpfCnpj: value
        }));
    }
  };

  const handleModalLegendaToggle = (event) => {
    event.preventDefault();
    setIsModalLegendaOpen(!isModalLegendaOpen);
  };

  const handleModalCadastroToggle = (event) => {
    event.preventDefault();
    setIsModalCadastroOpen(!isModalCadastroOpen);
  };

  {/* Consumindo API Clientes */}
  const getPosts = async () => {
    try {
      {/* URL API */}
      const response = await axios.get("http://localhost:8080/clientes");
      console.log(response.data);
      setPosts(response.data);
    } catch (error) {
      console.log(error);
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

  const handleCepChange = (e) => {
    const value = e.target.value.replace(/\D/g, '');
    const formattedValue = value.replace(/(\d{5})(\d{3})/, '$1-$2');
  
    setCep(formattedValue);
    
    if (formattedValue.length === 9) {
      getDadosEnderecoCEP(value);
    }
  };

// Função para formatar CPF
const formatCpf = (value) => {
  return value
      .replace(/\D/g, '') // Remove caracteres não numéricos
      .replace(/(\d{3})(\d)/, '$1.$2') // Adiciona o primeiro ponto
      .replace(/(\d{3})(\d)/, '$1.$2') // Adiciona o segundo ponto
      .replace(/(\d{3})(\d{1,2})$/, '$1-$2'); // Adiciona o traço
};

// Função para formatar CNPJ
const formatCnpj = (value) => {
  return value
      .replace(/\D/g, '') // Remove caracteres não numéricos
      .replace(/(\d{2})(\d)/, '$1.$2') // Adiciona o primeiro ponto
      .replace(/(\d{3})(\d)/, '$1/$2') // Adiciona a barra
      .replace(/(\d{4})(\d{1,2})$/, '$1-$2'); // Adiciona o traço
};


  {/* Pegando dados da API ao digitar o CNPJ */}
  const getDadosCNPJ = async (cnpjDigitado) => {
    try {
        // Remove caracteres não numéricos antes da chamada
        const cnpjFormatted = cnpjDigitado.replace(/\D/g, '');
        
        const responseCNPJ = await axios.get(`https://www.receitaws.com.br/v1/cnpj/${cnpjFormatted}`);

        // Verifica se houve erro na resposta
        if (responseCNPJ.data.erro) {
            throw new Error('CNPJ não encontrado.');
        }

        // Atualiza o estado com os dados retornados
        setFormData((prevData) => ({
            ...prevData,
            fantasia: responseCNPJ.data.fantasia || '',
            cep: responseCNPJ.data.cep || '',
            email: responseCNPJ.data.email || '',
            complemento: responseCNPJ.data.complemento || '',
            nomeRazao: responseCNPJ.data.nome || '',
        }));
        setError(null);
    } catch (error) {
        setError('Erro ao buscar CNPJ: ' + error.message);
        // Limpa os campos em caso de erro
        setFormData((prevData) => ({
            ...prevData,
            fantasia: '',
            cep: '',
            email: '',
            complemento: '',
            nomeRazao: '',
        }));
    }
};


  useEffect(() => {
    getPosts();
  }, []);

  {/* Sort da tabela */}
  const handleSort = (column) => {
    const direction = (sortColumn === column && sortDirection === 'asc') ? 'desc' : 'asc';
    setSortColumn(column);
    setSortDirection(direction);
  };

  const sortedPosts = [...posts].sort((a, b) => {
    const aValue = a[sortColumn];
    const bValue = b[sortColumn];

    if (typeof aValue === 'string' && typeof bValue === 'string') {
      return sortDirection === 'asc' ? aValue.localeCompare(bValue) : bValue.localeCompare(aValue);
    }
    return sortDirection === 'asc' ? aValue - bValue : bValue - aValue;
  });

  {/* Colunas da tabela */}
  const tableColumns = [
    { label: '', key: 'action' },
    { label: 'ID', key: 'id' },
    { label: 'Nome/Razão', key: 'nomeRazao' },
    { label: 'Fantasia', key: 'fantasia' },
    { label: 'Documento', key: 'cpfCnpj' },
    { label: 'Município', key: 'municipio' },
    { label: 'UF', key: 'uf' },
    { label: 'Telefone', key: 'telefone' },
    { label: 'Ativo', key: 'ativo' },
    { label: 'Última Compra', key: 'ultimaCompra' },
  ];

  {/* Modal de Cadastro */}
  const handleInputChange = (e) => {
    const { name, value } = e.target;

    if (name === 'cpfCnpj') {
        const digitsOnly = value.replace(/\D/g, '');
        let formattedValue = value;

        if (digitsOnly.length === 11) {
          formattedValue = formatCpf(value);
      } else if (digitsOnly.length === 14) {
          formattedValue = formatCnpj(value);
      }
        setFormData((prevData) => ({ ...prevData, [name]: formattedValue }));
    }

    // Ação para o CEP
    if (name === 'cep' && value.length === 8) {
        getDadosEnderecoCEP(value);
    }

    // Ação para CPF ou CNPJ
    if (name === 'cpfCnpj' && value.length === 14) {
        getDadosCNPJ(value);
    }
};


  {/* Upload de Imagem */}
  const handleImageChange = (event) => {
    const file = event.target.files[0];
    if (file) {
      setSelectedImage(URL.createObjectURL(file));
    }
  };


  {/* Post ADD Tabela */}
  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
        const response = await axios.post("http://localhost:8080/clientes", formData);
        setFormData({
            nomeRazao: '',
            fantasia: '',
            documento: '',
            cep: '',
            municipio: '',
            uf: '',
            bairro: '',
            logradouro: '',
            numero: '',
            complemento: '',
            cpfCnpj: '',
            pfOuPj: '',
            email: '',
            telefone: '',
            celular: '',
            contato: '',
            rgInscricaoEstadual: '',
            inscricaoEstadualMunicipal: '',
            observacao: '',
            ativo: true,
        });
        setIsModalCadastroOpen(false);
        getPosts();
    } catch (error) {
        console.error('Erro ao cadastrar cliente:', error.response ? error.response.data : error.message);
    }
  };

  return (
    <div className="flex h-screen overflow-hidden">
      {/* Sidebar */}
      <Sidebar sidebarOpen={sidebarOpen} setSidebarOpen={setSidebarOpen} />
      {/* Conteúdo */}
      <div className="relative flex flex-col flex-1 overflow-y-auto overflow-x-hidden">
        <Header sidebarOpen={sidebarOpen} setSidebarOpen={setSidebarOpen} />

        {/* Modal de Cadastro - Visual */}
        {isModalCadastroOpen && (
          <div className="fixed inset-0 z-50 flex items-center justify-center bg-black bg-opacity-50">
            <div className="bg-white rounded-lg p-6 w-8/12 max-h-fit overflow-auto">
              <h3 className="text-lg font-semibold text-center mb-4">Cadastro de Cliente</h3>
              <form onSubmit={handleSubmit} className="space-y-6">

                {/* Img / Nome / Documento*/}
                <div className="flex mb-4 gap-4">
                <label className="flex items-center cursor-pointer">
                  <FontAwesomeIcon icon={faImage} size="8x" />
                  <input
                    type="file"
                    accept="image/*"
                    onChange={handleImageChange}
                    className="hidden"
                  />
                </label>
                {selectedImage && (
                  <img src={selectedImage} alt="Preview" className="w-32 h-32 object-cover" />
                )}
                  <input
                    type="text"
                    name="nomeRazao"
                    placeholder="Nome"
                    value={formData.nomeRazao}
                    onChange={handleInputChange}
                    className="h-11 w-96 px-3 py-2 mt-2 rounded-md ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-indigo-600"
                    required
                  />
                  <Menu as="div">
                    <div>
                      <MenuButton className="w-60 h-11 px-3 py-2 mt-2 rounded-md bg-white text-sm font-semibold text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 hover:bg-gray-50">
                        {documentoValue || 'Tipo de Documento'}
                      </MenuButton>
                    </div>
                    <MenuItems className="absolute z-10 mt-1 w-60 origin-top-right rounded-md bg-white shadow-lg ring-1 ring-black ring-opacity-5 transition focus:outline-none">
                      <div className="py-1">
                        {['CPF', 'CNPJ'].map(item => (
                          <MenuItem key={item}>
                            <a
                              className="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100"
                              onClick={() => handleDocumentoItemClick(item)}
                            >
                              {item}
                            </a>
                          </MenuItem>
                        ))}
                      </div>
                    </MenuItems>
                  </Menu>
                  <input
                    type="text"
                    name="cpfCnpj"
                    placeholder="Documento"
                    value={docDigitado}
                    onChange={handleInputChange}
                    disabled={!isDocumentoSelected}
                    className="h-11 px-3 py-2 mt-2 rounded-md ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                  />
                </div>
                
                {/* Dados do Cliente */}
                <div className="flex gap-4">
                  
                <input
                  type="text"
                  name="fantasia"
                  placeholder="Fantasia"
                  value={formData.fantasia}
                  onChange={handleInputChange}
                  disabled={documentoValue === 'CPF'}
                  className="h-11 w-96 px-3 py-2 rounded-md ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                />
                </div>

                {/* Endereço do Cliente */}
                <div className="flex gap-4">
                <input
                  type="text"
                  name="cep"
                  placeholder="CEP"
                  value={cep}
                  onChange={handleCepChange}
                  className="h-11 w-32 px-3 py-2 rounded-md ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-indigo-600"
                />
                </div>
                <div className="flex gap-4">
                  
                  <input
                    type="text"
                    name="municipio"
                    placeholder="Cidade / Município"
                    value={formData.municipio}
                    onChange={handleInputChange}
                    readOnly
                    className="h-11 w-64 px-3 py-2 rounded-md ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-indigo-600"
                  />
                  <input
                    type="text"
                    name="uf"
                    placeholder="Estado"
                    value={formData.uf}
                    onChange={handleInputChange}
                    readOnly
                    className="h-11 w-20 px-3 py-2 rounded-md ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-indigo-600"
                  />
                  <input
                    type="text"
                    name="bairro"
                    placeholder="Bairro"
                    value={formData.bairro}
                    onChange={handleInputChange}
                    readOnly
                    className="h-11 w-96 px-3 py-2 rounded-md ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-indigo-600"
                  />
                  <input
                    type="text"
                    name="logradouro"
                    placeholder="Rua / Logradouro"
                    value={formData.logradouro}
                    onChange={handleInputChange}
                    readOnly
                    className="h-11 w-64 px-3 py-2 rounded-md ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-indigo-600"
                  />
                  <input
                    type="text"
                    name="numero"
                    placeholder="Número"
                    value={formData.numero}
                    onChange={handleInputChange}
                    className="h-11 w-24 px-3 py-2 rounded-md ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-indigo-600"
                  />
                </div>
                <div className="flex gap-4">
                  <input
                      type="text"
                      name="complemento"
                      placeholder="Complemento"
                      value={formData.complemento}
                      onChange={handleInputChange}
                      className="h-11 px-3 py-2 rounded-md ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-indigo-600"
                  />
                </div>
                <div className="flex gap-4">
                <input
                    type="text"
                    name="rgInscricaoEstadual"
                    placeholder="RG Inscrição Estadual"
                    value={formData.rgInscricaoEstadual}
                    onChange={handleInputChange}
                    className="h-11 w-56 px-3 py-2 rounded-md ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-indigo-600"
                  />
                  <input
                    type="text"
                    name="inscricaoEstadualMunicipal"
                    placeholder="Inscrição Estadual Municipal"
                    value={formData.inscricaoEstadualMunicipal}
                    onChange={handleInputChange}
                    className="h-11 px-3 py-2 rounded-md ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-indigo-600"
                  />
                </div>
                {/* Ativo e Revenda */}
                <div className="flex gap-4 mt-2">
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

                {/* Contato do Cliente */}
                <div className="flex gap-4">
                  <input
                    type="email"
                    name="email"
                    placeholder="Email"
                    value={formData.email}
                    onChange={handleInputChange}
                    className="h-11 w-96 px-3 py-2 rounded-md ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-indigo-600"
                  />
                  <input
                    type="text"
                    name="observacao"
                    placeholder="Observação"
                    value={formData.observacao}
                    onChange={handleInputChange}
                    className="h-11 w-full px-3 py-2 rounded-md ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-indigo-600"
                  />
                </div>
                <div className="flex gap-4">
                  <input
                    type="text"
                    name="telefone"
                    placeholder="Telefone"
                    value={formData.telefone}
                    onChange={handleInputChange}
                    className="h-11 w-64 px-3 py-2 rounded-md ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-indigo-600"
                  />
                  <input
                    type="text"
                    name="celular"
                    placeholder="Celular"
                    value={formData.celular}
                    onChange={handleInputChange}
                    className="h-11 px-3 py-2 rounded-md ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-indigo-600"
                  />
                  <input
                    type="text"
                    name="contato"
                    placeholder="Contato"
                    value={formData.contato}
                    onChange={handleInputChange}
                    className="h-11 px-3 py-2 rounded-md ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-indigo-600"
                  />
                </div>
                <div className="flex justify-end gap-4 mt-4">
                  <button type="submit" className="h-11 px-4 py-2 rounded-md bg-indigo-600 text-white hover:bg-indigo-700">
                    Cadastrar
                  </button>
                  <button type="button" onClick={() => setIsModalCadastroOpen(false)} className="h-11 px-4 py-2 rounded-md bg-gray-300 text-gray-800 hover:bg-gray-400">
                    Cancelar
                  </button>
                </div>
              </form>
            </div>
          </div>
        )}


        {/* Form Clientes */}
        <form>
          <div className="space-y-12 mt-10 ml-10">
            <div className="border-b border-gray-900/10 pb-12">
              <h2 className="text-base font-semibold leading-7 text-gray-900">
                Listagem de Clientes
              </h2>
              <div className="mt-10 grid grid-cols-1 gap-x-6 gap-y-8 sm:grid-cols-4 mr-10">
                <div className="sm:col-span-4">
                  <div className="mt-2 flex">
                    <div className="flex-initial w-full">
                      <label htmlFor="input1" className="block text-sm font-medium leading-6 text-gray-900">Descrição</label>
                      <div className="flex rounded-md shadow-sm ring-1 ring-inset ring-gray-300 focus-within:ring-2 focus-within:ring-inset focus-within:ring-indigo-600 sm:max-w-md">
                        <span className="flex select-none items-center pl-3 text-gray-500 sm:text-sm">
                          <FontAwesomeIcon icon={faUser} />
                        </span>
                        <input
                          type="text"
                          id="input1"
                          className="block w-full ml-3 rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                        />
                      </div>
                      <div className="flex-auto w-full">
                        <label htmlFor="input1" className="block text-sm font-medium leading-6 text-gray-900">Filtros</label>
                        <div className="flex rounded-md sm:max-w-md">
                          <Menu as="div" className="flex rounded-md">
                            <div>
                              <MenuButton className="w-56 rounded-md bg-white px-3 py-2 text-sm font-semibold text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 hover:bg-gray-50">
                                {campoValue || 'Selecione um Campo'}
                              </MenuButton>
                            </div>
                            <MenuItems
                              transition
                              className="absolute z-10 mt-10 w-56 origin-top-right rounded-md bg-white shadow-lg ring-1 ring-black ring-opacity-5 transition focus:outline-none data-[closed]:scale-95 data-[closed]:transform data-[closed]:opacity-0 data-[enter]:duration-100 data-[leave]:duration-75 data-[enter]:ease-out data-[leave]:ease-in"
                            >
                              <div className="py-1">
                                {['Todos', 'Documento', 'Nome', 'Fantasia', 'Contato', 'Município', 'UF', 'Telefone', 'Celular', 'Última compra', 'Data de nascimento', 'Incompleto'].map(item => (
                                  <MenuItem key={item}>
                                    <a
                                      className="block px-4 py-2 text-sm text-gray-700 data-[focus]:bg-gray-100 data-[focus]:text-gray-900"
                                      onClick={() => handleMenuItemClick(item)}
                                    >
                                      {item}
                                    </a>
                                  </MenuItem>
                                ))}
                              </div>
                            </MenuItems>
                          </Menu>
                          <button
                            type="button"
                            className="w-10 ml-4 rounded-md bg-white px-2 py-2 text-sm font-semibold text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 hover:bg-gray-50"
                            onClick={handleModalLegendaToggle}
                          >
                            <FontAwesomeIcon icon={faCircleInfo} />
                          </button>
                          <button className="w-44 ml-4 rounded-md bg-indigo-600 text-white hover:bg-indigo-700">
                            Pesquisar
                          </button>
                        </div>
                      </div>
                    </div>
                  </div>

                  {/* Tabela */}
                  <div className="overflow-x-auto overflow-y-auto mt-5">
                    <div className="max-h-[24rem] overflow-y-auto">
                      <table className="min-w-full max-w-full divide-y divide-gray-200">
                        <thead className="bg-gray-100">
                          <tr>
                            {tableColumns.map((column, index) => (
                              <th key={index} className="px-6 py-3 text-center text-sm font-medium text-gray-700">
                                <div className="flex items-center justify-center cursor-pointer" onClick={() => handleSort(column.key)}>
                                  <span className="mr-1">{column.label}</span>
                                  {index !== 0 && <FontAwesomeIcon icon={faSort} />}
                                </div>
                              </th>
                            ))}
                          </tr>
                        </thead>
                        <tbody className="bg-white divide-y divide-gray-200">
                          {sortedPosts.map((data) => (
                            <tr key={data.id}>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700 text-center">
                                <FontAwesomeIcon icon={faMagnifyingGlass} className="cursor-pointer" />
                              </td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700 text-center">{data.id}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700 text-center">{data.nomeRazao}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700 text-center">{data.fantasia}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700 text-center">{data.cpfCnpj}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700 text-center">{data.municipio}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700 text-center">{data.uf}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700 text-center">{data.telefone}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700 text-center">{data.ativo ? 'Sim' : 'Não'}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700 text-center">{data.ultimaCompra}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700 text-center">
                                <FontAwesomeIcon icon={faPenToSquare} className="cursor-pointer" />
                              </td>
                            </tr>
                          ))}
                        </tbody>
                      </table>
                    </div>
                  </div>

                  {/* Modal de legendas da tabela */}
                  {isModalLegendaOpen && (
                    <div className="fixed inset-0 z-50 flex items-center justify-center bg-black bg-opacity-50">
                      <div className="bg-white rounded-lg p-6 w-96">
                        <h3 className="text-lg font-semibold">Legenda da tabela</h3>
                        <ul className="mt-4 space-y-2">
                          <li>Documento: Número de identificação do cliente.</li>
                          <li>Nome: Nome completo do cliente.</li>
                          <li>Fantasia: Nome fantasia do cliente.</li>
                          <li>Contato: Nome da pessoa de contato.</li>
                          <li>Município: Cidade onde o cliente reside.</li>
                          <li>UF: Unidade Federativa.</li>
                          <li>Telefone/Celular: Números de contato.</li>
                          <li>Ativo: Indica se o cliente está ativo.</li>
                          <li>Última compra: Data da última compra realizada.</li>
                          <li>Data de Nascimento: Data de nascimento do cliente.</li>
                        </ul>
                        <div className="mt-4 flex justify-end">
                          <button
                            className="rounded-md bg-indigo-600 px-4 py-2 text-white"
                            onClick={handleModalLegendaToggle}
                          >
                            OK
                          </button>
                        </div>
                      </div>
                    </div>
                  )}
                </div>
              </div>
              {/* Botão cadastrar novo cliente */}
              <div className="mr-10 mt-10 h-10">
                <button className="w-auto float-end px-3 py-2 rounded-md bg-indigo-600 text-white hover:bg-indigo-700"
                  onClick={handleModalCadastroToggle}>
                  Cadastrar
                </button>
              </div>
            </div>
          </div>
        </form>
      </div>
    </div>
  );
}

export default Clientes;