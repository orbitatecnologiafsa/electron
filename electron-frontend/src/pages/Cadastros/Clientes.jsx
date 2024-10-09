import React, { useState } from 'react';
import Sidebar from '../../partials/Sidebar';
import Header from '../../partials/Header';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUser, faSort, faCircleInfo } from '@fortawesome/free-solid-svg-icons';
import { Menu, MenuButton, MenuItems, MenuItem } from '@headlessui/react';

function Clientes() {
  const [sidebarOpen, setSidebarOpen] = useState(false);
  const [campoValue, setCampoValue] = useState('Selecione um Campo');
  const [isModalOpen, setIsModalOpen] = useState(false);

  const handleMenuItemClick = (value) => {
    setCampoValue(value);
  };

  const handleModalToggle = () => {
    setIsModalOpen(!isModalOpen);
  };

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
                Listagem de Clientes
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
                              <MenuButton className="w-52 rounded-md bg-white px-3 py-2 text-sm font-semibold text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 hover:bg-gray-50">
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
                            className="w-25 ml-14 rounded-md bg-white px-3 py-2 text-sm font-semibold text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 hover:bg-gray-50"
                            onClick={handleModalToggle}
                          >
                            <FontAwesomeIcon icon={faCircleInfo} />
                          </button>
                          
                          <button className="w-25 ml-14 rounded-md bg-white px-3 py-2 text-sm font-semibold text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 hover:bg-gray-50">
                            Pesquisar
                          </button>
                        </div>
                      </div>
                    </div>
                  </div>

                  {/* Tabela */}
                  <div className="overflow-x-auto overflow-y-auto mt-5">
                    <table className="min-w-full max-w-full divide-y divide-gray-200">
                      <thead className="bg-gray-100">
                        <tr>
                          <th className="px-4 py-3 text-left text-sm font-medium text-gray-700">Documento <FontAwesomeIcon icon={faSort} /></th>
                          <th className="px-4 py-3 text-left text-sm font-medium text-gray-700">Nome <FontAwesomeIcon icon={faSort} /></th>
                          <th className="px-4 py-3 text-left text-sm font-medium text-gray-700">Fantasia <FontAwesomeIcon icon={faSort} /></th>
                          <th className="px-4 py-3 text-left text-sm font-medium text-gray-700">Contato <FontAwesomeIcon icon={faSort} /></th>
                          <th className="px-4 py-3 text-left text-sm font-medium text-gray-700">Município <FontAwesomeIcon icon={faSort} /></th>
                          <th className="px-4 py-3 text-left text-sm font-medium text-gray-700">UF <FontAwesomeIcon icon={faSort} /></th>
                          <th className="px-4 py-3 text-left text-sm font-medium text-gray-700">Telefone <FontAwesomeIcon icon={faSort} /></th>
                          <th className="px-4 py-3 text-left text-sm font-medium text-gray-700">Celular <FontAwesomeIcon icon={faSort} /></th>
                          <th className="px-4 py-3 text-left text-sm font-medium text-gray-700">Ativo <FontAwesomeIcon icon={faSort} /></th>
                          <th className="px-4 py-3 text-left text-sm font-medium text-gray-700">Última compra <FontAwesomeIcon icon={faSort} /></th>
                          <th className="px-4 py-3 text-left text-sm font-medium text-gray-700">Data de Nascimento <FontAwesomeIcon icon={faSort} /></th>
                        </tr>
                      </thead>
                      <tbody className="bg-white divide-y divide-gray-200">
                        {/* Exemplo de dados */}
                        {[...Array(5)].map((_, index) => (
                          <tr key={index}>
                            <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700">123456789</td>
                            <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700">João Silva</td>
                            <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700">Silva Comércio</td>
                            <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700">Contato 1</td>
                            <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700">São Paulo</td>
                            <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700">SP</td>
                            <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700">+55 11 98765-4321</td>
                            <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700">+55 11 91234-5678</td>
                            <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700">Sim</td>
                            <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700">01/10/2023</td>
                            <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700">01/01/2000</td>
                          </tr>
                        ))}
                      </tbody>
                    </table>
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
                  <button className="w-auto float-end  rounded-md bg-white px-3 py-2 text-sm font-semibold text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 hover:bg-gray-50">
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
