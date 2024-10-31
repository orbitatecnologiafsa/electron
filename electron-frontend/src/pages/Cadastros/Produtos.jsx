import React, { useState } from 'react';
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

  const handleMenuItemClick = (value) => {
    setCampoValue(value);
  };

  const handleStatusItemClick = (value) => {
    setStatusValue(value);
  };

  const handleModalToggle = () => {
    setIsModalOpen(!isModalOpen);
  };

  {/* Colunas da Tabela */}
  const tableColumns = [
    '',
    'Código',
    'Barras',
    'Nome',
    'UN',
    'Quantidade',
    'Qtd Bloqueada',
    'Qtd Disponível',
    'Qtd Ideal',
    'Preço Custo',
    'Custo Médio',
    'Preço Venda',
    'Preço Revenda',
    'Descrição',
    'Contr. Lote',
    'Contr. Serial',
    'Contr. Grade',
    'Grupo',
    'NCM',
    'CEST',
    'Trib. Estadual',
    'Trib. Federal',
    'Referência',
    'Status',
  ];

  {/* Dados da Tabela */}
  const exampleData = [
    {
      index: "",
      codigo: '001',
      barras: '7891234567890',
      nome: 'Produto A',
      un: 'kg',
      quantidade: 100,
      qtdBloqueada: 10,
      qtdDisponivel: 90,
      qtdIdeal: 120,
      precoCusto: 10.00,
      custoMedio: 10.50,
      precoVenda: 15.00,
      precoRevenda: 20.00,
      descricao: 'Descrição do Produto A',
      contrLote: 'Sim',
      contrSerial: 'Não',
      contrGrade: 'Não',
      grupo: 'Grupo A',
      ncm: '1234.56.78',
      cest: '12.345.67',
      tribEstadual: 'ICMS',
      tribFederal: 'PIS/COFINS',
      referencia: 'REF001',
      status: 'Ativo',
    },
    {
      codigo: '002',
      barras: '7891234567891',
      nome: 'Produto B',
      un: 'un',
      quantidade: 200,
      qtdBloqueada: 20,
      qtdDisponivel: 180,
      qtdIdeal: 250,
      precoCusto: 5.00,
      custoMedio: 5.50,
      precoVenda: 8.00,
      precoRevenda: 12.00,
      descricao: 'Descrição do Produto B',
      contrLote: 'Não',
      contrSerial: 'Sim',
      contrGrade: 'Não',
      grupo: 'Grupo B',
      ncm: '2345.67.89',
      cest: '23.456.78',
      tribEstadual: 'ICMS',
      tribFederal: 'PIS/COFINS',
      referencia: 'REF002',
      status: 'Ativo',
    },
  ];

  {/* Redireciona para Cadastro de Clientes */}
  const handleRedirect = () => {
    navigate('/cadastro/produtos/adicionar');
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
                  <div className="overflow-x-auto mt-5">
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
                              <td className="px-14 py-4 whitespace-nowrap text-sm text-gray-700">{data.codigo}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700">{data.barras}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700">{data.nome}</td>
                              <td className="px-10 py-4 whitespace-nowrap text-sm text-gray-700">{data.un}</td>
                              <td className="px-14 py-4 whitespace-nowrap text-sm text-gray-700">{data.quantidade}</td>
                              <td className="px-20 py-4 whitespace-nowrap text-sm text-gray-700">{data.qtdBloqueada}</td>
                              <td className="px-20 py-4 whitespace-nowrap text-sm text-gray-700">{data.qtdDisponivel}</td>
                              <td className="px-14 py-4 whitespace-nowrap text-sm text-gray-700">{data.qtdIdeal}</td>
                              <td className="px-14 py-4 whitespace-nowrap text-sm text-gray-700">{data.precoCusto.toFixed(2)}</td>
                              <td className="px-14 py-4 whitespace-nowrap text-sm text-gray-700">{data.custoMedio.toFixed(2)}</td>
                              <td className="px-14 py-4 whitespace-nowrap text-sm text-gray-700">{data.precoVenda.toFixed(2)}</td>
                              <td className="px-20 py-4 whitespace-nowrap text-sm text-gray-700">{data.precoRevenda.toFixed(2)}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700">{data.descricao}</td>
                              <td className="px-14 py-4 whitespace-nowrap text-sm text-gray-700">{data.contrLote}</td>
                              <td className="px-20 py-4 whitespace-nowrap text-sm text-gray-700">{data.contrSerial}</td>
                              <td className="px-20 py-4 whitespace-nowrap text-sm text-gray-700">{data.contrGrade}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700">{data.grupo}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700">{data.ncm}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700">{data.cest}</td>
                              <td className="px-20 py-4 whitespace-nowrap text-sm text-gray-700">{data.tribEstadual}</td>
                              <td className="px-20 py-4 whitespace-nowrap text-sm text-gray-700">{data.tribFederal}</td>
                              <td className="px-14 py-4 whitespace-nowrap text-sm text-gray-700">{data.referencia}</td>
                              <td className="px-14 py-4 whitespace-nowrap text-sm text-gray-700">{data.status}</td>
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
