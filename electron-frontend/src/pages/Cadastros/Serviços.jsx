import React, {  useEffect, useState } from 'react';
import Sidebar from '../../partials/Sidebar';
import Header from '../../partials/Header';
import { useNavigate } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faMagnifyingGlass, faSort, faHandHoldingDollar } from '@fortawesome/free-solid-svg-icons';
import { Menu, MenuButton, MenuItems, MenuItem } from '@headlessui/react';
import DropDown from '../../components/DropDown';

function Servicos() {
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

  const handleModalToggle = () => {
    setIsModalOpen(!isModalOpen);
  };

  {/* Comandos de Filtragem */}
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
  
  const getPosts = async () => {
    try {
      {/* URL API */}
      const response = await axios.get("http://localhost:8080/produtos");
      console.log(response.data);
      setPosts(response.data);
    } catch (error) {
      console.error("Erro ao buscar dados: ", error);
    }
    
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

        if (campoValue === 'Grupo') {
          fieldValue = data.grupoProdServ?.nome;
        } else if(campoValue === 'Tributação Estadual') {
          fieldValue = data.tributacaoEstadual?.nome;
        }else if (campoValue === 'CEST'){
          fieldValue = data.codigoCestNcm?.codigo;
        }else if (campoValue === 'NCM'){
          fieldValue = data.codigoCestNcm?.tributoNcm?.codigo;
        }else if (campoValue === 'Tributação Federal'){
          fieldValue = data.tributacaoFederal?.nome;
	      }else {
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

  {/*Filtro*/}
  const campoValueMapping = {
    'Todos': null,
    'ID': 'id',
    'Barras': 'codigo',
    'Nome': 'nome',
    'Uni. Saída': 'unidadeSaida',
    'Atividade': 'atividade',
    'Grupo': 'grupoProdServ',
    'Custo': 'precoCusto',
    'Venda': 'precoVenda',
    'Revenda': 'precoRevenda',
    'Tributação Estadual': 'tributacaoEstadual',
    'Tributação Federal': 'tributacaoFederal',
  };

  const handleRedirect = () => {
    navigate('/cadastro/servicos/adicionar');
  };

  {/* Colunas da Tabela */}
  const tableColumns = [
    { label: '', key: 'action' },
    { label: 'ID', key: 'id' },
    { label: 'Barras', key: 'barras' },
    { label: 'Nome', key: 'nome' },
    { label: 'Uni. Saída', key: 'unidadeSaida' },
    { label: 'Atividade', key: 'atividade' },
    { label: 'Grupo', key: 'grupoProdServ' },
    { label: 'Preço custo', key: 'precoCusto' },
    { label: 'Preço venda', key: 'precoVenda' },
    { label: 'Preço revenda', key: 'precoRevenda' },
    { label: 'Trib. Estadual', key: 'tributacaoEstadual' },
    { label: 'Trib. Federal', key: 'tributacaoFederal' },
  ];

  return (
    <div className="flex h-screen overflow-hidden">
      {/* Sidebar */}
      <Sidebar sidebarOpen={sidebarOpen} setSidebarOpen={setSidebarOpen} />

      {/* Conteúdo */}
      <div className="relative flex flex-col flex-1 overflow-y-auto overflow-x-hidden">
        {/* Site header */}
        <Header sidebarOpen={sidebarOpen} setSidebarOpen={setSidebarOpen} />
        {/* Form Servicos */}
        <form>
          <div className="space-y-12 mt-10 ml-10">
            <div className="border-b border-gray-900/10 pb-12">
              <h2 className="text-base font-semibold leading-7 text-gray-900">
                Listagem de Serviços
              </h2>
              <div className="mt-10 grid grid-cols-1 gap-x-6 gap-y-8 sm:grid-cols-4 mr-10">
                <div className="sm:col-span-4">
                  <div className="mt-2 flex">
                    {/* Busca do produto */}
                    <div className="flex-initial w-full">
                      <label htmlFor="input1" className="block text-sm font-medium leading-6 text-gray-900">Descrição</label>
                      <div className="flex rounded-md shadow-sm ring-1 ring-inset ring-gray-300 focus-within:ring-2 focus-within:ring-inset focus-within:ring-indigo-600 sm:max-w-md">
                        <span className="flex select-none items-center pl-3 text-gray-500 sm:text-sm">
                          <FontAwesomeIcon icon={faHandHoldingDollar} />
                        </span>
                        <input
                          type="text"
                          id="input1"
                          className="block w-full ml-3 rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                          onChange={handleInputFilterChange}
                        />
                      </div>
                      {/* Filtros de busca */}
                      <div className="flex-auto w-full">
                        <label htmlFor="input1" className="block text-sm font-medium leading-6 text-gray-900">Filtros</label>
                        <div className="flex rounded-md sm:max-w-md">
                          <DropDown title={"Selecione um Campo"} ValorBtn={campoValue} listItens={['Todos', 'Código', 'Barras', 'Nome', 'Descrição', 'Grupo', 'Tributação Estadual', 'Tributação Federal']} onSelect={(item) => handleMenuItemClick(item)}/>
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
                  <div className="overflow-x-auto mt-5">
                    <div className="max-h-[24rem] overflow-y-auto">
                      <table className="min-w-full max-w-full divide-y divide-gray-200">
                        <thead className="bg-gray-100">
                          <tr>
                            {tableColumns.map((column, index) => (
                              <th key={column} className="px-6 py-3 text-center text-sm font-medium text-gray-700">
                                <div className="flex items-center justify-center cursor-pointer" onClick={() => handleSort(column.key)}>
                                  <span className="mr-1">{column.label}</span>
                                  {index !== 0 && <FontAwesomeIcon icon={faSort} />}
                                </div>
                              </th>
                            ))}
                          </tr>
                        </thead>
                        <tbody className="bg-white divide-y divide-gray-200 text-center">
                          {sortedPosts.map((data, index) => (
                            <tr key={index}>
                              <td className="px-4 py-4 whitespace-nowrap text-sm text-gray-700">
                                <FontAwesomeIcon icon={faMagnifyingGlass} />
                              </td>
                              <td className="px-14 py-4 whitespace-nowrap text-sm text-gray-700">{data.id}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700">{data.barras}</td>
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700">{data.nome}</td>
                              <td className="px-10 py-4 whitespace-nowrap text-sm text-gray-700">{data.unidadeSaida}</td>
                              <td className="px-14 py-4 whitespace-nowrap text-sm text-gray-700">{data.grupoProdServ}</td>
                              <td className="px-20 py-4 whitespace-nowrap text-sm text-gray-700">{data.precoCusto}</td>
                              <td className="px-20 py-4 whitespace-nowrap text-sm text-gray-700">{data.precoVenda}</td>
                              <td className="px-14 py-4 whitespace-nowrap text-sm text-gray-700">{data.precoRevenda}</td>
                              <td className="px-14 py-4 whitespace-nowrap text-sm text-gray-700">{data.tributacaoEstadual}</td>
                              <td className="px-14 py-4 whitespace-nowrap text-sm text-gray-700">{data.tributacaoFederal}</td>
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

export default Servicos;
