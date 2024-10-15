import React, { useEffect, useState } from 'react';
import Sidebar from '../../partials/Sidebar';
import Header from '../../partials/Header';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUser, faSort, faCircleInfo, faMagnifyingGlass } from '@fortawesome/free-solid-svg-icons';
import axios from 'axios';
import { Menu, MenuButton, MenuItems, MenuItem } from '@headlessui/react';

function Clientes() {
  const [sidebarOpen, setSidebarOpen] = useState(false);
  const [campoValue, setCampoValue] = useState('Selecione um Campo');
  const [isModalLegendaOpen, setIsModalLegendaOpen] = useState(false);
  const [isModalCadastroOpen, setIsModalCadastroOpen] = useState(false);
  const [posts, setPosts] = useState([]);
  const [sortColumn, setSortColumn] = useState('id'); 
  const [sortDirection, setSortDirection] = useState('asc'); 
  const [formData, setFormData] = useState({
    nome: '',
    fantasia: '',
    documento: '',
    municipio: '',
    uf: '',
    telefone: '',
    ativo: true,
    ultimaCompra: '',
    dataNascimento: '',
  });

  const handleMenuItemClick = (value) => {
    setCampoValue(value);
  };

  const handleModalLegendaToggle = (event) => {
    event.preventDefault();
    setIsModalLegendaOpen(!isModalLegendaOpen);
  };

  const handleModalCadastroToggle = (event) => {
    event.preventDefault();
    setIsModalCadastroOpen(!isModalCadastroOpen);
  };

  {/* Consumindo API */}
  const getPosts = async () => {
    try {
      const response = await axios.get("http://localhost:8080/api/clientes");
      setPosts(response.data);
    } catch (error) {
      console.log(error);
    }
  };

  useEffect(() => {
    getPosts();
  }, []);

  {/* Sort da tabela pela coluna */}
  const handleSort = (column) => {
    const direction = (sortColumn === column && sortDirection === 'asc') ? 'desc' : 'asc';
    setSortColumn(column);
    setSortDirection(direction);
  };

  const sortedPosts = [...posts].sort((a, b) => {
    const aValue = a[sortColumn];
    const bValue = b[sortColumn];

    if (aValue < bValue) return sortDirection === 'asc' ? -1 : 1;
    if (aValue > bValue) return sortDirection === 'asc' ? 1 : -1;
    return 0;
  });

  {/* Colunas da  tabela */}
  const tableColumns = [
    '',
    'ID',
    'Nome',
    'Fantasia',
    'Documento',
    'Município',
    'UF',
    'Telefone',
    'Ativo',
    'Última compra',
  ];

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.post("http://localhost:8080/api/clientes", formData);
      setFormData({
        nome: '',
        fantasia: '',
        documento: '',
        municipio: '',
        uf: '',
        telefone: '',
        ativo: true,
        ultimaCompra: '',
        dataNascimento: '',
      });
      setIsModalCadastroOpen(false);
      {/* Get Posts para atualizar lista após cadastro no modal */}
      getPosts();
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <div className="flex h-screen overflow-hidden">
      {/* Sidebar */}
      <Sidebar sidebarOpen={sidebarOpen} setSidebarOpen={setSidebarOpen} />
      {/* Conteúdo */}
      <div className="relative flex flex-col flex-1 overflow-y-auto overflow-x-hidden">
        <Header sidebarOpen={sidebarOpen} setSidebarOpen={setSidebarOpen} />
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
                    {/* Busca do Cliente */}
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
                      {/* Filtros da busca */}
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
                          <button className="w-44 ml-4 rounded-md bg-white px-3 py-2 text-sm font-semibold text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 hover:bg-gray-50">
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
                              <th key={column} className="px-6 py-3 text-center text-sm font-medium text-gray-700">
                                {index === 0 ? (
                                  column
                                ) : (
                                  <div className="flex items-center justify-center cursor-pointer" onClick={() => handleSort(column)}>
                                    <span className="mr-1">{column}</span>
                                    <FontAwesomeIcon icon={faSort} />
                                  </div>
                                )}
                              </th>
                            ))}
                          </tr>
                        </thead>
                        <tbody className="bg-white divide-y divide-gray-200 text-center">
                          {sortedPosts.map((data, index) => (
                            <tr key={index}>
                              <td className="px-4 py-4 whitespace-nowrap text-center text-sm text-gray-700">
                                <FontAwesomeIcon icon={faMagnifyingGlass} />
                              </td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700">{data.id}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700">{data.nomeRazao}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700">{data.fantasia}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700">{data.cpfCnpj}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700">{data.municipio}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700">{data.uf}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700">{data.telefone}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700">{data.ativo ? 'Sim' : 'Não'}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700">{data.ultimaCompra || 'N/A'}</td>
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

                  {/* Modal de Cadastro de Clientes */}
                  {isModalCadastroOpen && (
                    <div className="fixed inset-0 z-50 flex items-center justify-center bg-black bg-opacity-50">
                      <div className="bg-white rounded-lg p-6 w-96">
                        <h3 className="text-lg font-semibold">Cadastro de Cliente</h3>
                        <form onSubmit={handleSubmit} className="mt-4 space-y-4 ">
                          <input
                            type="text"
                            name="nome"
                            placeholder="Nome"
                            value={formData.nome}
                            onChange={handleInputChange}
                            className="w-full px-3 py-2 rounded-md ring-1 ring-inset ring-gray-300 focus-within:ring-2 focus-within:ring-inset focus-within:ring-indigo-600 sm:max-w-md"
                            required
                          />
                          <input
                            type="text"
                            name="fantasia"
                            placeholder="Fantasia"
                            value={formData.fantasia}
                            onChange={handleInputChange}
                            className="w-full px-3 py-2 rounded-md ring-1 ring-inset ring-gray-300 focus-within:ring-2 focus-within:ring-inset focus-within:ring-indigo-600 sm:max-w-md"
                          />
                          <input
                            type="text"
                            name="documento"
                            placeholder="Documento"
                            value={formData.documento}
                            onChange={handleInputChange}
                            className="w-full px-3 py-2 rounded-md ring-1 ring-inset ring-gray-300 focus-within:ring-2 focus-within:ring-inset focus-within:ring-indigo-600 sm:max-w-md"
                          />
                          <input
                            type="text"
                            name="municipio"
                            placeholder="Município"
                            value={formData.municipio}
                            onChange={handleInputChange}
                            className="w-full px-3 py-2 rounded-md ring-1 ring-inset ring-gray-300 focus-within:ring-2 focus-within:ring-inset focus-within:ring-indigo-600 sm:max-w-md"
                          />
                          <input
                            type="text"
                            name="uf"
                            placeholder="UF"
                            value={formData.uf}
                            onChange={handleInputChange}
                            className="w-full px-3 py-2 rounded-md ring-1 ring-inset ring-gray-300 focus-within:ring-2 focus-within:ring-inset focus-within:ring-indigo-600 sm:max-w-md"
                          />
                          <input
                            type="text"
                            name="telefone"
                            placeholder="Telefone"
                            value={formData.telefone}
                            onChange={handleInputChange}
                            className="w-full px-3 py-2 rounded-md ring-1 ring-inset ring-gray-300 focus-within:ring-2 focus-within:ring-inset focus-within:ring-indigo-600 sm:max-w-md"
                          />
                          <div className="flex items-center">
                            <input
                              type="checkbox"
                              name="ativo"
                              checked={formData.ativo}
                              onChange={() => setFormData({ ...formData, ativo: !formData.ativo })}
                              className="mr-2 rounded checked:bg-indigo-600"
                            />
                            <label className="text-sm">Ativo</label>
                          </div>
                          <div className="flex justify-between">
                            <button type="submit" className="mt-4 rounded-md bg-indigo-600 hover:bg-indigo-800 px-4 py-2 text-white">
                              Cadastrar
                            </button>
                            <button
                              className="mt-4 bg-white rounded-md px-4 py-2 text-sm font-semibold text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 hover:bg-gray-50"
                              onClick={handleModalCadastroToggle}
                            >
                              Cancelar
                            </button>
                          </div>
                        </form>
                      </div>
                    </div>
                  )}
                </div>
              </div>
              <div className="mr-10 mt-10 h-10">
                <button className="w-auto float-end rounded-md bg-white px-3 py-2 text-sm font-semibold text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 hover:bg-gray-50"
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
