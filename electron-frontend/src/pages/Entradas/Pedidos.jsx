import React, { useState } from 'react';
import Sidebar from '../../partials/Sidebar';
import Header from '../../partials/Header';
import { useNavigate } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUser, faSort, faCalendar, faMagnifyingGlass } from '@fortawesome/free-solid-svg-icons';
import { Menu, MenuButton, MenuItems, MenuItem } from '@headlessui/react';

function Pedidos() {
  const [sidebarOpen, setSidebarOpen] = useState(false);
  const [campoValue, setCampoValue] = useState('Selecione um Campo');
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [startDate, setStartDate] = useState('');
  const [endDate, setEndDate] = useState('');
  const [isFiltered, setIsFiltered] = useState(false);

  const handleMenuItemClick = (value) => {
    setCampoValue(value);
  };

  const handleModalToggle = () => {
    setIsModalOpen(!isModalOpen);
    if(!isModalOpen){
      setIsFiltered(false);
    }
  };

  const handleFilterByDates = () => {
    const formattedStartDate = formatarDataParaBR(startDate);
    const formattedEndDate = formatarDataParaBR(endDate);
    setIsFiltered(true);
    handleModalToggle();
  };

  const formatarDataParaBR = (dateString) => {
    const date = new Date(dateString);
    return `${String(date.getDate()).padStart(2, '0')}/${String(date.getMonth() + 1).padStart(2, '0')}/${date.getFullYear()}`;
  };

  // Colunas da Tabela
  const tableColumns = [
    '',
    'Documento',
    'Nome',
    'Fantasia',
    'Contato',
    'Município',
    'UF',
    'Telefone',
    'Celular',
    'Ativo',
    'Última compra',
    'Data de Nascimento',
  ];

  // Dados da tabela
  const exampleData = [
    {
      index: "",
      documento: '123456789',
      nome: 'João Silva',
      fantasia: 'Silva Comércio',
      contato: 'Contato 1',
      municipio: 'São Paulo',
      uf: 'SP',
      telefone: '+55 11 98765-4321',
      celular: '+55 11 91234-5678',
      ativo: 'Sim',
      ultimaCompra: '01/10/2023',
      dataNascimento: '01/01/2000',
    },
  ];

  const handleRedirect = () => {
    navigate('/entradas/novopedido');
  };
  const navigate = useNavigate();

  return (
    <div className="flex h-screen overflow-hidden">
      {/* Sidebar */}
      <Sidebar sidebarOpen={sidebarOpen} setSidebarOpen={setSidebarOpen} />

      {/* Conteúdo */}
      <div className="relative flex flex-col flex-1 overflow-y-auto overflow-x-hidden">
        {/* Site header */}
        <Header sidebarOpen={sidebarOpen} setSidebarOpen={setSidebarOpen} />
        {/* Form Clientes */}
        <form>
          <div className="space-y-12 mt-10 ml-10">
            <div className="border-b border-gray-900/10 pb-12">
              <h2 className="text-base font-semibold leading-7 text-gray-900">
                Listagem Pedidos de compra
              </h2>
              <div className="mt-10 grid grid-cols-1 gap-x-6 gap-y-8 sm:grid-cols-4 mr-10">
                <div className="sm:col-span-4">
                  <div className="mt-2 flex">
                    {/* Busca do cliente */}
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
                      {/* Filtros de busca */}
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
                            onClick={handleModalToggle}
                          >
                            <FontAwesomeIcon icon={faCalendar} />
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
                                {index === 0 ? column : <>{column} <FontAwesomeIcon icon={faSort} /></>}
                              </th>
                            ))}
                          </tr>
                        </thead>
                        <tbody className="bg-white divide-y divide-gray-200 text-center">
                          {exampleData.map((data, index) => (
                            <tr key={index}>
                              <td className="px-4 py-4 whitespace-nowrap text-sm text-gray-700">
                                <FontAwesomeIcon icon={faMagnifyingGlass} />
                              </td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700">{data.documento}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700">{data.nome}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700">{data.fantasia}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700">{data.contato}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700">{data.municipio}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700">{data.uf}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700">{data.telefone}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700">{data.celular}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700">{data.ativo}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700">{data.ultimaCompra}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700">{data.dataNascimento}</td>
                            </tr>
                          ))}
                        </tbody>
                      </table>
                    </div>
                  </div>

                  {/* Modal para filtragem de datas */}
                  {isModalOpen && (
                    <div className="fixed inset-0 z-50 flex items-center justify-center bg-black bg-opacity-50">
                      <div className="bg-white rounded-lg p-6 w-96">
                        <h3 className="text-lg font-semibold">Filtrar por datas</h3>
                        <div className="mt-4 space-y-4">
                          <div>
                            <label htmlFor="startDate" className="block text-sm font-medium text-gray-700">
                              Data inicial:
                            </label>
                            <input
                              type="date"
                              id="startDate"
                              className="border border-gray-300 rounded-md px-3 py-2 mt-1 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 block w-full"
                              value={startDate}
                              onChange={(e) => setStartDate(e.target.value)}
                            />
                          </div>
                          <div>
                            <label htmlFor="endDate" className="block text-sm font-medium text-gray-700">
                              Data final:
                            </label>
                            <input
                              type="date"
                              id="endDate"
                              className="border border-gray-300 rounded-md px-3 py-2 mt-1 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 block w-full"
                              value={endDate}
                              onChange={(e) => setEndDate(e.target.value)}
                            />
                          </div>
                        </div>
                        <div className="mt-4 flex justify-end">
                          <button
                            className="rounded-md bg-indigo-600 px-4 py-2 text-white mr-2"
                            onClick={handleFilterByDates}
                          >
                            Filtrar
                          </button>
                          <button
                            className="rounded-md bg-gray-300 px-4 py-2 text-gray-700"
                            onClick={handleModalToggle} // Função para fechar o modal
                          >
                            Cancelar
                          </button>
                        </div>
                      </div>
                    </div>
                  )}
                </div>
              </div>
                <div className="flex mt-10 justify-around">
                {isFiltered && (
                  <>
                    <p>Data Inicial: {startDate && formatarDataParaBR(startDate)}</p>
                    <p>Data Final: {endDate && formatarDataParaBR(endDate)}</p>
                  </>
                )}
                </div>
                <div className="mr-10 mt-10 h-10">
                <button type="button" onClick={handleRedirect} className="w-auto float-end px-3 py-2 rounded-md bg-indigo-600 text-white hover:bg-indigo-700">
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

export default Pedidos;
