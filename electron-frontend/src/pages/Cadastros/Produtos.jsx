import React, { useEffect, useState } from 'react';
import Sidebar from '../../partials/Sidebar';
import { useNavigate } from 'react-router-dom';
import Header from '../../partials/Header';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faMagnifyingGlass, faSort } from '@fortawesome/free-solid-svg-icons';
import { Menu, MenuButton, MenuItems, MenuItem } from '@headlessui/react';

function Produtos() {
  const [sidebarOpen, setSidebarOpen] = useState(false);
  const [campoValue, setCampoValue] = useState('Selecione um Campo');
  const [statusValue, setStatusValue] = useState('Status');
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [inputInfo, setInputInfo] = useState('');
  const [filteredPosts, setFilteredPosts] = useState([]);
  const [searchInfo, setSearchInfo] = useState('');

  const sortedPosts = [...filteredPosts].sort((a, b) => {
    const aValue = a[sortColumn];
    const bValue = b[sortColumn];

    if (typeof aValue === 'string' && typeof bValue === 'string') {
      return sortDirection === 'asc' ? aValue.localeCompare(bValue) : bValue.localeCompare(aValue);
    }
    return sortDirection === 'asc' ? aValue - bValue : bValue - aValue;
  });

  const handleInputFilterChange = (e) => {
    setInputInfo(e.target.value);
  };

  const handleSearchClick = (event) => {
    event.preventDefault();
    setSearchInfo(inputInfo);
  };

  {/* Sort da tabela */}
  const handleSort = (column) => {
    const direction = (sortColumn === column && sortDirection === 'asc') ? 'desc' : 'asc';
    setSortColumn(column);
    setSortDirection(direction);
  };
  

  {/* Const API Clientes */}
  const [posts, setPosts] = useState([]);
  const [sortColumn, setSortColumn] = useState('id'); 

  useEffect(() => {
    if (campoValue === 'Todos') {
      setFilteredPosts(posts);
    } else if (searchInfo) {
      const propertyName = campoValueMapping[campoValue];
      const filteredData = posts.filter(data => {
        const fieldValue = data[propertyName];
        return fieldValue && fieldValue.toString().toLowerCase() === searchInfo.toLowerCase();
      });
    
      setFilteredPosts(filteredData);
    } else {
      setFilteredPosts([]);
    }
  }, [searchInfo, posts, campoValue]);

  const [sortDirection, setSortDirection] = useState('asc'); 
  const navigate = useNavigate();
  const handleMenuItemClick = (value) => {
    setCampoValue(value);
  };

  const handleStatusItemClick = (value) => {
    setStatusValue(value);
  };

  const handleModalToggle = () => {
    setIsModalOpen(!isModalOpen);
  };


  {/* Colunas da tabela */}
  const tableColumns = [
    { label: '', key: 'action' },
    { label: 'ID', key: 'id' },
    { label: 'Barras', key: 'razaoSocial' },
    { label: 'Nome', key: 'nomeFantasia' },
    { label: 'UN', key: 'cpfCnpj' },
    { label: 'Quantidade', key: 'matrizOuFilial' },
    { label: 'Qtd. bloqueada', key: 'municipio' },
    { label: 'Qtd. disponível', key: 'uf' },
    { label: 'Qtd. ideal', key: 'telefone' },
    { label: 'Preço custo', key: 'ativo' },
    { label: 'Custo médio', key: 'dataCriacao' },
    { label: 'Preço venda', key: 'dataCriacao' },
    { label: 'Preço revenda', key: 'dataCriacao' },
    { label: 'Descrição', key: 'dataCriacao' },
    { label: 'Contr. lote', key: 'dataCriacao' },
    { label: 'Contr. serial', key: 'dataCriacao' },
    { label: 'Contr. Grade', key: 'dataCriacao' },
    { label: 'Grupo', key: 'dataCriacao' },
    { label: 'NCM', key: 'dataCriacao' },
    { label: 'CEST', key: 'dataCriacao' },
    { label: 'Trib. Estadual', key: 'dataCriacao' },
    { label: 'Trib. Federal', key: 'dataCriacao' },
    { label: 'Referência', key: 'dataCriacao' },
    { label: 'Status', key: 'dataCriacao' },
  ];

  {/* Redireciona para Cadastro de Produtos */}
  const handleRedirect = () => {
    navigate('/cadastro/produtos/adicionar');
  };

  return (
    <div className="flex h-screen overflow-hidden">
      {/* Sidebar */}
      <Sidebar sidebarOpen={sidebarOpen} setSidebarOpen={setSidebarOpen} />

      {/* Conteúdo */}
      <div className="relative flex flex-col flex-1 overflow-y-auto overflow-x-hidden">
        {/* Site header */}
        <Header sidebarOpen={sidebarOpen} setSidebarOpen={setSidebarOpen} />
        {/* Form Produtos */}
        <form>
          <div className="space-y-12 mt-10 ml-10">
            <div className="border-b border-gray-900/10 pb-12">
              <h2 className="text-base font-semibold leading-7 text-gray-900">
                Listagem de Produtos
              </h2>
              <div className="mt-10 grid grid-cols-1 gap-x-6 gap-y-8 sm:grid-cols-4 mr-10">
                <div className="sm:col-span-4">
                  <div className="mt-2 flex">
                    {/* Busca do produto */}
                    <div className="flex-initial w-full">
                      <label htmlFor="input1" className="block text-sm font-medium leading-6 text-gray-900">Descrição</label>
                      <div className="flex rounded-md shadow-sm ring-1 ring-inset ring-gray-300 focus-within:ring-2 focus-within:ring-inset focus-within:ring-indigo-600 sm:max-w-md">
                        <span className="flex select-none items-center pl-3 text-gray-500 sm:text-sm">
                          <FontAwesomeIcon icon={faMagnifyingGlass} />
                        </span>
                        <input
                          type="text"
                          id="input1"
                          className="block w-full ml-3 rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                        />
                      </div>
                      {/* Filtros de busca */}
                      <div className="flex-auto w-full">
                        <label htmlFor="input1" className="block text-sm font-medium leading-6 text-gray-900">Filtros</label>
                        <div className="flex rounded-md sm:max-w-md">
                        <Menu as="div" className="flex rounded-md">
                            <div>
                            <MenuButton className="w-44 rounded-md bg-white px-3 py-2 text-sm font-semibold text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 hover:bg-gray-50">
                                {campoValue || 'Selecione um Campo'}
                              </MenuButton>
                            </div>
                            <MenuItems
                              transition
                              className="absolute z-10 mt-10 w-44 origin-top-right rounded-md bg-white shadow-lg ring-1 ring-black ring-opacity-5 transition focus:outline-none data-[closed]:scale-95 data-[closed]:transform data-[closed]:opacity-0 data-[enter]:duration-100 data-[leave]:duration-75 data-[enter]:ease-out data-[leave]:ease-in"
                            >
                              <div className="py-1">
                                {['Todos', 'Código', 'Barras', 'Nome', 'Descrição', 'Grupo', 'NCM', 'CEST', 'Tributação Estadual', 'Tributação Federal', 'Referência', 'Localização', 'Unidade'].map(item => (
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
                          {/* Botão de status */}
                          <Menu as="div" className="flex rounded-md ml-4">
                            <div>
                              <MenuButton className="w-32 rounded-md bg-white px-3 py-2 text-sm font-semibold text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 hover:bg-gray-50">
                                {statusValue || 'Status'}
                              </MenuButton>
                            </div>
                            <MenuItems
                              transition
                              className="absolute z-10 mt-10 w-32 origin-top-right rounded-md bg-white shadow-lg ring-1 ring-black ring-opacity-5 transition focus:outline-none data-[closed]:scale-95 data-[closed]:transform data-[closed]:opacity-0 data-[enter]:duration-100 data-[leave]:duration-75 data-[enter]:ease-out data-[leave]:ease-in"
                            >
                              <div className="py-1">
                                {['Ativo', 'Inativo', 'Todos'].map(item => (
                                  <MenuItem key={item}>
                                    <a
                                      className="block px-4 py-2 text-sm text-gray-700 data-[focus]:bg-gray-100 data-[focus]:text-gray-900"
                                      onClick={() => handleStatusItemClick(item)}
                                    >
                                      {item}
                                    </a>
                                  </MenuItem>
                                ))}
                              </div>
                            </MenuItems>
                          </Menu>
                          <button className="w-32 ml-4 rounded-md bg-white px-3 py-2 text-sm font-semibold text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 hover:bg-gray-50">
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
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700 text-center">{data.Barras}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700 text-center">{data.Nome}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700 text-center">{data.un}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700 text-center">{data.Quantidade}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700 text-center">{data.Quantidade}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700 text-center">{data.Quantidade}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700 text-center">{data.Quantidade}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700 text-center">{data.Preço}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700 text-center">{data.Preço}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700 text-center">{data.Preço}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700 text-center">{data.Preço}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700 text-center">{data.Descrição}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700 text-center">{data.Contr}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700 text-center">{data.Contr}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700 text-center">{data.Contr}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700 text-center">{data.Grupo}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700 text-center">{data.telefone}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700 text-center">{data.ncm}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700 text-center">{data.cest}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700 text-center">{data.Trib}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700 text-center">{data.Trib}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700 text-center">{data.Referência}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700 text-center">{data.Status}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700 text-center">
                                <FontAwesomeIcon icon={faPenToSquare} className="cursor-pointer" />
                              </td>
                            </tr>
                          ))}
                        </tbody>
                      </table>
                    </div>
                  </div>


                  {/* Modal */}
                  {isModalOpen && (
                    <div className="fixed inset-0 z-50 flex items-center justify-center bg-black bg-opacity-50">
                      <div className="bg-white rounded-lg p-6 w-96">
                        <h3 className="text-lg font-semibold">Legenda da tabela</h3>
                        <ul className="mt-4 space-y-2">
                          <li>Item 1: Descrição do primeiro item.</li>
                          <li>Item 2: Descrição do segundo item.</li>
                          <li>Item 3: Descrição do terceiro item.</li>
                        </ul>
                        <div className="mt-4 flex justify-end">
                          <button
                            className="rounded-md bg-indigo-600 px-4 py-2 text-white"
                            onClick={handleModalToggle}
                          >
                            OK
                          </button>
                        </div>
                      </div>
                    </div>
                  )}
                </div>
              </div>
              <div className="mr-10 mt-10 h-10">
                <button className="w-auto float-end px-3 py-2 rounded-md bg-indigo-600 text-white hover:bg-indigo-700"
                  onClick={handleRedirect}>
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

export default Produtos;
