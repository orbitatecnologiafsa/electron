import React, { useState } from 'react';
import Sidebar from '../../partials/Sidebar';
import Header from '../../partials/Header';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUser, faSort, faCalendar, faMagnifyingGlass } from '@fortawesome/free-solid-svg-icons';
import { Menu, MenuButton, MenuItems, MenuItem } from '@headlessui/react';

function Caixa() {
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
    'CFOP Origem',
    'CFOP Convertido',
  ];

  // Dados da tabela
  const exampleData = [
    {
      index: "",
      cfoporigem: '????',
      cfopconvertido: '???',
    },
  ];

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
                Relação CFOP
              </h2>
              <div className="mt-10 grid grid-cols-1 gap-x-6 gap-y-8 sm:grid-cols-4 mr-10">
                <div className="sm:col-span-4">
                  <div className="mt-2 flex">                
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
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700">{data.cfoporigem}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700">{data.cfopconvertido}</td>
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
                <button className="w-auto float-end rounded-md bg-white px-3 py-2 text-sm font-semibold text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 hover:bg-gray-50">
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

export default Caixa;
