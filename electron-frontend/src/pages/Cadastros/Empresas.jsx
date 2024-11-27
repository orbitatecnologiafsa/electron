import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Sidebar from '../../partials/Sidebar';
import Header from '../../partials/Header';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUser, faSort, faCircleInfo, faMagnifyingGlass, faPenToSquare } from '@fortawesome/free-solid-svg-icons';
import axios from 'axios';
import DropDown from '../../components/DropDown';


function Empresas() {
  const [sidebarOpen, setSidebarOpen] = useState(false);
  const [campoValue, setCampoValue] = useState('Todos');
  const [isEditModalOpen, setIsEditModalOpen] = useState(false);
  const [currentData, setCurrentData] = useState(null);

  {/* Const de modal */}
  const [isModalLegendaOpen, setIsModalLegendaOpen] = useState(false);

  {/* Const API Empresas */}
  const [posts, setPosts] = useState([]);
  const [sortColumn, setSortColumn] = useState('id'); 

  const navigate = useNavigate();

  {/* Sort da Tabela */}
  const [sortDirection, setSortDirection] = useState('asc'); 
  const [inputInfo, setInputInfo] = useState('');
  const [filteredPosts, setFilteredPosts] = useState([]);
  const [searchInfo, setSearchInfo] = useState('');

  const handleSort = (column) => {
    const direction = (sortColumn === column && sortDirection === 'asc') ? 'desc' : 'asc';
    setSortColumn(column);
    setSortDirection(direction);
  };

  {/* Redireciona para Cadastro de Clientes */}
  const handleRedirect = () => {
    navigate('/cadastro/empresas/adicionar');
  };

  const handleModalLegendaToggle = (event) => {
    event.preventDefault();
    setIsModalLegendaOpen(!isModalLegendaOpen);
  };

  {/* Consumindo API Empresas */}
  const getPosts = async () => {
    try {
      {/* URL API */}
      const response = await axios.get("http://localhost:8080/empresas-proprietarias");
      console.log(response.data);
      setPosts(response.data);
    } catch (error) {
      console.error("Erro ao buscar dados: ", error);
    }
    
  };

  const handleEditClick = (data) => {
    setCurrentData(data);
    setIsEditModalOpen(true);
  };
  
  const handleCloseEditModal = () => {
    setIsEditModalOpen(false);
  };

  const handleMenuItemClick = (value) => {
    setCampoValue(value);
  };

  useEffect(() => {
    getPosts();
  }, []);

  useEffect(() => {
    if (campoValue === 'Todos') {
      setFilteredPosts(posts);
      console.log("Aqui os posts: ", posts);
    } else if (searchInfo) {
      console.log("searchInfo:", searchInfo);
      const propertyName = campoValueMapping[campoValue];
  
      console.log("propertyName:", propertyName);
  
      const filteredData = posts.filter(data => {
        let fieldValue;
  
        // Se o campo selecionado for "UF", acesse o estado dentro de municipio
        if (campoValue === 'UF') {
          fieldValue = data.municipio?.estado?.nome;
        } else {
          fieldValue = data[propertyName];
        }
  
        return fieldValue && fieldValue.toString().toLowerCase().includes(searchInfo.toLowerCase());
      });
  
      setFilteredPosts(filteredData);
      console.log(filteredData);
    } else {
      setFilteredPosts([]);
    }
  }, [searchInfo, posts, campoValue]);
  
  const handleInputFilterChange = (e) => {
    setInputInfo(e.target.value);
  };
  
  const handleSearchClick = (event) => {
    event.preventDefault();
    if (campoValue) {
      console.log("campoValue:",campoValue);
        setSearchInfo(inputInfo);
    } else {
        setFilteredPosts(posts);
    }
    console.log("InputInfo: ",inputInfo);
    console.log("Seache: ",searchInfo);
  };

  const sortedPosts = [...filteredPosts].sort((a, b) => { 
    const aValue = a[sortColumn];
    const bValue = b[sortColumn];

    // Caso o valor seja um objeto, extraia uma propriedade específica para comparação
    if (typeof aValue === 'object' && aValue !== null) {
        if (aValue.nome) {
            return sortDirection === 'asc' ? aValue.nome.localeCompare(bValue.nome) : bValue.nome.localeCompare(aValue.nome);
        }
    }

    if (typeof aValue === 'string' && typeof bValue === 'string') {
      return sortDirection === 'asc' ? aValue.localeCompare(bValue) : bValue.localeCompare(aValue);
    }
    return sortDirection === 'asc' ? aValue - bValue : bValue - aValue;
});

  const tableColumns = [
    { label: '', key: 'action' },
    { label: 'ID', key: 'id' },
    { label: 'Razão Social', key: 'razaoSocial' },
    { label: 'Nome Fantasia', key: 'nomeFantasia' },
    { label: 'CNPJ', key: 'cpfCnpj' },
    { label: 'Matriz / Filial', key: 'tipoUnidade' },
    { label: 'Município', key: 'municipio' },
    { label: 'UF', key: 'estado' },
    { label: 'Telefone', key: 'telefone' },
    { label: 'Ativo', key: 'ativo' },
    { label: 'Data de Integração', key: 'dataCriacao' },
  ];

  const campoValueMapping = {
    'Todos': null,
    'ID': 'id',
    'Razão Social': 'razaoSocial',
    'Nome Fantasia': 'nomeFantasia',
    'CNPJ': 'cpfCnpj',
    'Matriz / Filial': 'tipoUnidade',
    'Município': 'municipio',
    'UF': 'estado',
    'Telefone': 'telefone',
    'Ativo': 'ativo',
    'Data de Integração': 'dataCriacao'
  };

  return (
    <div className="flex h-screen overflow-hidden">
      {/* Sidebar */}
      <Sidebar sidebarOpen={sidebarOpen} setSidebarOpen={setSidebarOpen} />
      {/* Conteúdo */}
      <div className="relative flex flex-col flex-1 overflow-y-auto overflow-x-hidden">
        <Header sidebarOpen={sidebarOpen} setSidebarOpen={setSidebarOpen} />
        {/* Form Empresas */}
        <form>
          <div className="space-y-12 mt-10 ml-10">
            <div className="border-b border-gray-900/10 pb-12">
              <h2 className="text-base font-semibold leading-7 text-gray-900">
                Listagem de Empresas
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
                          onChange={handleInputFilterChange}
                        />
                      </div>
                      <div className="flex-auto w-full">
                        <label htmlFor="input1" className="block text-sm font-medium leading-6 text-gray-900">Filtros</label>
                        <div className="flex rounded-md sm:max-w-md">
                        <DropDown title={"Selecione um Campo"} ValorBtn={campoValue} listItens={["Todos","ID", "Razão Social", "Nome Fantasia", "CNPJ", "Matriz / Filial", "Município", "UF", "Telefone","Ativo","Data de Integração"]} onSelect={(item) => handleMenuItemClick(item)}/>

                        <button
                            type="button"
                            className="w-10 ml-4 rounded-md bg-white px-2 py-2 text-sm font-semibold text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 hover:bg-gray-50"
                            onClick={handleModalLegendaToggle}
                          >
                            <FontAwesomeIcon icon={faCircleInfo} />
                          </button>
                          
                          <button
                            type="button"
                            className="w-44 ml-4 rounded-md bg-indigo-600 text-white hover:bg-indigo-700"
                            onClick={handleSearchClick}
                          >
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
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700 text-center">{data.razaoSocial}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700 text-center">{data.nomeFantasia}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700 text-center">{data.cpfCnpj}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700 text-center">{data.tipoUnidade}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700 text-center">{data.municipio?.nome}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700 text-center">{data.municipio?.estado?.nome}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700 text-center">{data.telefone}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700 text-center">{data.ativo ? 'Sim' : 'Não'}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700 text-center">{data.dataCriacao}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700 text-center">
                                <FontAwesomeIcon
                                  icon={faPenToSquare}
                                  className="cursor-pointer"
                                  onClick={() => handleEditClick(data)}
                                />
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
                  onClick={handleRedirect}>
                  Cadastrar
                </button>
              </div>
            </div>
          </div>
        </form>
      </div>

      {isEditModalOpen && (
        <div className="fixed inset-0 z-50 flex items-center justify-center bg-black bg-opacity-50">
          <div className="bg-white rounded-lg p-6 w-[34rem] h-auto">
            <h3 className="text-lg font-semibold">Editar Empresa</h3>
            {/* Exemplo de campos de formulário, você pode customizar com base nos dados */}
            {currentData && (
              <form>
                <div className="flex flex-col md:flex-row gap-4">
                  <div className="mt-4">
                    <label htmlFor="razaoSocial" className="block text-sm font-medium text-gray-900">Razão Social</label>
                    <input
                      type="text"
                      id="razaoSocial"
                      value={currentData.razaoSocial}
                      className="mt-2 block w-full rounded-md border-gray-300 shadow-sm"
                    />
                  </div>
                  <div className="mt-4">
                    <label htmlFor="cpfCnpj" className="block text-sm font-medium text-gray-900">CNPJ</label>
                    <input
                      type="text"
                      id="cpfCnpj"
                      value={currentData.cpfCnpj}
                      className="mt-2 block w-full rounded-md border-gray-300 shadow-sm"
                    />
                  </div>
                </div>
                <div className="mt-4">
                  <label htmlFor="nomeFantasia" className="block text-sm font-medium text-gray-900">Nome Fantasia</label>
                  <input
                    type="text"
                    id="nomeFantasia"
                    value={currentData.nomeFantasia}
                    className="mt-2 block w-full rounded-md border-gray-300 shadow-sm"
                  />
                </div>
                <div className="flex flex-col md:flex-row gap-4">
                  <div className="mt-4">
                    <label className="block text-sm font-medium text-gray-900">RG Inscrição Estadual</label>
                    <input
                      type="text"
                      name="rgInscricaoEstadual"
                      value={currentData.rgInscricaoEstadual}
                      className="mt-2 block w-full rounded-md border-gray-300 shadow-sm"
                    />
                    </div>
                    <div className="mt-4">
                    <label className="block text-sm font-medium text-gray-900">Inscrição Estadual Municipal</label>
                    <input
                      type="text"
                      name="inscricaoEstadualMunicipal"
                      value={currentData.inscricaoEstadualMunicipal}
                      className="mt-2 block w-full rounded-md border-gray-300 shadow-sm"
                    />
                  </div>
                </div>
                <div className="flex flex-col md:flex-row gap-4">
                  <div className="mt-4">
                    <label className="block text-sm font-medium text-gray-900">Email</label>
                    <input
                      type="text"
                      name="rgInscricaoEstadual"
                      value={currentData.email}
                      className="mt-2 block w-full rounded-md border-gray-300 shadow-sm"
                    />
                    </div>
                    <div className="mt-4">
                    <label className="block text-sm font-medium text-gray-900">Contato</label>
                    <input
                      type="text"
                      name="inscricaoEstadualMunicipal"
                      value={currentData.contato}
                      className="mt-2 block w-full rounded-md border-gray-300 shadow-sm"
                    />
                  </div>
                </div>
                <div className="flex flex-col md:flex-row gap-4">
                  <div className="mt-4">
                    <label className="block text-sm font-medium text-gray-900">Telefone</label>
                    <input
                      type="text"
                      name="rgInscricaoEstadual"
                      value={currentData.telefone}
                      className="mt-2 block w-full rounded-md border-gray-300 shadow-sm"
                    />
                    </div>
                    <div className="mt-4">
                    <label className="block text-sm font-medium text-gray-900">Celular</label>
                    <input
                      type="text"
                      name="inscricaoEstadualMunicipal"
                      value={currentData.celular}
                      className="mt-2 block w-full rounded-md border-gray-300 shadow-sm"
                    />
                  </div>
                </div>
                <div className="flex flex-col md:flex-row gap-4">
                  <div className="mt-4">
                    <label className="block text-sm font-medium text-gray-900">Observação</label>
                    <textarea
                      type="text"
                      name="rgInscricaoEstadual"
                      value={currentData.observacoes}
                      className="mt-2 block w-[31rem] h-[96px] rounded-md border-gray-300 shadow-sm resize-none"
                    />
                  </div>
                </div>
                {/* Adicione mais campos conforme necessário */}
                <div className="mt-6 flex justify-end">
                  <button
                    type="button"
                    className="bg-blue-500 text-white px-4 py-2 rounded-md"
                    onClick={() => handleCloseEditModal()}
                  >
                    Fechar
                  </button>
                  <button
                    type="submit"
                    className="ml-4 bg-green-500 text-white px-4 py-2 rounded-md"
                    // Aqui você pode adicionar a lógica de salvar as alterações
                  >
                    Salvar
                  </button>
                </div>
              </form>
            )}
          </div>
        </div>
      )}
    </div>
  );
}

export default Empresas;