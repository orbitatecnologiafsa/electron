import React, { useEffect, useState } from 'react';
import Sidebar from '../../partials/Sidebar';
import Header from '../../partials/Header';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faDolly, faSort, faCircleInfo, faMagnifyingGlass, faPenToSquare } from '@fortawesome/free-solid-svg-icons';
import axios from 'axios';
import { Menu, MenuButton, MenuItems, MenuItem } from '@headlessui/react';

function Clientes() {
  const [sidebarOpen, setSidebarOpen] = useState(false);
  const [campoValue, setCampoValue] = useState('Selecione um Campo');
  const [documentoValue, setDocumentoValue] = useState(null);;
  const [isDocumentoSelected, setIsDocumentoSelected] = useState(false);
  const [tipoCliente, setTipoCliente] = useState('-');
  const [isModalLegendaOpen, setIsModalLegendaOpen] = useState(false);
  const [isModalCadastroOpen, setIsModalCadastroOpen] = useState(false);
  const [posts, setPosts] = useState([]);
  const [sortColumn, setSortColumn] = useState('id'); 
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

  const handleModalLegendaToggle = (event) => {
    event.preventDefault();
    setIsModalLegendaOpen(!isModalLegendaOpen);
  };

  const handleModalCadastroToggle = (event) => {
    event.preventDefault();
    setIsModalCadastroOpen(!isModalCadastroOpen);
  };

  {/* Consumindo API Fornecedores */}
  const getPosts = async () => {
    try {
      {/* URL API */}
      const response = await axios.get("http://localhost:8080/api/fornecedores");
      console.log(response.data);
      setPosts(response.data);
    } catch (error) {
      console.log(error);
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
    setFormData((prevData) => ({ ...prevData, [name]: value }));
  };


  {/* Post ADD Tabela */}
  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
        const response = await axios.post("http://localhost:8080/api/fornecedores", formData);
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
            <h3 className="text-lg font-semibold">Cadastro de Fornecedor</h3>
            <form onSubmit={handleSubmit} className="space-y-6 mt-10">
              
              {/* Tipo de Documento */}
              <Menu as="div" className="flex justify-center mb-4">
                <div>
                  <MenuButton className="w-60 h-11 px-3 py-2 rounded-md bg-white text-sm font-semibold text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 hover:bg-gray-50">
                    {documentoValue || 'Tipo de Documento'}
                  </MenuButton>
                </div>
                <MenuItems className="absolute z-10 mt-10 w-60 origin-top-right rounded-md bg-white shadow-lg ring-1 ring-black ring-opacity-5 transition focus:outline-none">
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
              
              {/* Dados do Cliente */}
              <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                <input
                  type="text"
                  name="nomeRazao"
                  placeholder="Nome"
                  value={formData.nomeRazao}
                  onChange={handleInputChange}
                  className="h-11 px-3 py-2 rounded-md ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-indigo-600"
                  required
                />
                <input
                  type="text"
                  name="fantasia"
                  placeholder="Fantasia"
                  value={formData.fantasia}
                  onChange={handleInputChange}
                  className="h-11 px-3 py-2 rounded-md ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-indigo-600"
                />
                <input
                  type="text"
                  name="rgInscricaoEstadual"
                  placeholder="RG Inscrição Estadual"
                  value={formData.rgInscricaoEstadual}
                  onChange={handleInputChange}
                  className="h-11 px-3 py-2 rounded-md ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-indigo-600"
                />
                <input
                  type="text"
                  name="cpfCnpj"
                  placeholder="Preencha o documento"
                  value={formData.cpfCnpj}
                  onChange={handleInputChange}
                  disabled={!isDocumentoSelected}
                  className="h-11 px-3 py-2 rounded-md ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                />
              </div>

              {/* Endereço do Cliente */}
              <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
                <input
                  type="text"
                  name="cep"
                  placeholder="CEP"
                  value={formData.cep}
                  onChange={handleInputChange}
                  className="h-11 px-3 py-2 rounded-md ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-indigo-600"
                />
                <input
                  type="text"
                  name="municipio"
                  placeholder="Cidade / Município"
                  value={formData.municipio}
                  onChange={handleInputChange}
                  className="h-11 px-3 py-2 rounded-md ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-indigo-600"
                />
                <input
                  type="text"
                  name="uf"
                  placeholder="Estado / UF"
                  value={formData.uf}
                  onChange={handleInputChange}
                  className="h-11 px-3 py-2 rounded-md ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-indigo-600"
                />
                <input
                  type="text"
                  name="bairro"
                  placeholder="Bairro"
                  value={formData.bairro}
                  onChange={handleInputChange}
                  className="h-11 px-3 py-2 rounded-md ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-indigo-600"
                />
                <input
                  type="text"
                  name="logradouro"
                  placeholder="Rua / Logradouro"
                  value={formData.logradouro}
                  onChange={handleInputChange}
                  className="h-11 px-3 py-2 rounded-md ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-indigo-600"
                />
                <input
                  type="text"
                  name="numero"
                  placeholder="Número"
                  value={formData.numero}
                  onChange={handleInputChange}
                  className="h-11 px-3 py-2 rounded-md ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-indigo-600"
                />
                <input
                  type="text"
                  name="complemento"
                  placeholder="Complemento"
                  value={formData.complemento}
                  onChange={handleInputChange}
                  className="h-11 px-3 py-2 rounded-md ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-indigo-600"
                />
                <div className="mt-2">
                  <input
                    type="checkbox"
                    name="ativo"
                    checked={formData.ativo}
                    onChange={() => setFormData({ ...formData, ativo: !formData.ativo })}
                    className="mr-2 p-2 rounded checked:bg-indigo-600"
                  />
                  <label className="text-base">Ativo</label>
                </div>
                <div className="mt-2">
                  <input
                    type="checkbox"
                    name="revenda"
                    checked={formData.revenda}
                    onChange={() => setFormData({ ...formData, revenda: !formData.revenda })}
                    className="mr-2 p-2 rounded checked:bg-indigo-600"
                  />
                  <label className="text-base">Revenda</label>
                </div>
              </div>

              {/* Contato do Cliente */}
              <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                <input
                  type="email"
                  name="email"
                  placeholder="Email"
                  value={formData.email}
                  onChange={handleInputChange}
                  className="h-11 px-3 py-2 rounded-md ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-indigo-600"
                />
                <input
                  type="text"
                  name="telefone"
                  placeholder="Telefone"
                  value={formData.telefone}
                  onChange={handleInputChange}
                  className="h-11 px-3 py-2 rounded-md ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-indigo-600"
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

              <div className="flex items-center float-end gap-6 mt-4">
                {/* Botões de ação aqui */}
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
                          <FontAwesomeIcon icon={faDolly} />
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