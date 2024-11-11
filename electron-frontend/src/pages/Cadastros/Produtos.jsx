import React, { useEffect, useState } from 'react';
import Sidebar from '../../partials/Sidebar';
import { useNavigate } from 'react-router-dom';
import Header from '../../partials/Header';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faMagnifyingGlass, faSort } from '@fortawesome/free-solid-svg-icons';
import DropDown from '../../components/DropDown';

function Produtos() {
  const [sidebarOpen, setSidebarOpen] = useState(false);
  const [campoValue, setCampoValue] = useState('Selecione um Campo');
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
  

  {/* Colunas da tabela */}
  const tableColumns = [
    { label: '', key: 'action' },
    { label: 'ID', key: 'id' },
    { label: 'Barras', key: 'barras' },
    { label: 'Nome', key: 'nome' },
    { label: 'Quantidade', key: 'quantidadeEmbalagem' },
    { label: 'Qtd. bloqueada', key: 'bloqueado' },
    { label: 'Qtd. disponível', key: 'disponivel' },
    { label: 'Preço custo', key: 'precoCusto' },
    { label: 'Custo médio', key: 'precoCustoMedio' },
    { label: 'Preço venda', key: 'precoVenda' },
    { label: 'Preço revenda', key: 'precoRevenda' },
    { label: 'Descrição', key: 'descricao' },
    { label: 'Grupo', key: 'grupoProdServ' },
    { label: 'NCM', key: 'codigoCestNcm' },
    { label: 'CEST', key: 'codigoCestNcm' },
    { label: 'Trib. Estadual', key: 'tributacaoEstadual' },
    { label: 'Trib. Federal', key: 'tributacaoFederal' },
    { label: 'Referência', key: 'referencia' },
    { label: 'Unidade', key: 'unidadeEmbalagem' },
  ];

  {/*Filtro*/}
  const campoValueMapping = {
    'Todos': null,
    'ID': 'id',
    'Barras': 'barras',
    'Nome': 'nome',
    'Grupo': 'grupoProdServ',
    'NCM': 'codigoCestNcm',
    'Município': 'municipio',
    'CEST': 'estado',
    'Tributação Estadual': 'tributacaoEstadual',
    'Tributação Federal': 'tributacaoFederal',
    'Referência': 'ativo',
    'Localização': 'ativo',
    'Unidade': 'unidadeEmbalagem',
    'Preço Rev.': 'precoRevenda',
    'Preço Venda': 'precoVenda',
    'Custo médio': 'precoCustoMedio',
    'Preço Custo': 'precoCusto',
    'Quantidade': 'quantidadeEmbalagem',
    'Qtd. disponível': 'disponivel',
    'Qtd. bloqueada': 'bloqueado',
  };

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
                      <div className="flex-auto w-full justify-between">
                        <label htmlFor="input1" className="block text-sm font-medium leading-6 text-gray-900">Filtros</label>
                        <div className="flex rounded-md sm:max-w-md">
                          <DropDown title={"Selecione um Campo"} ValorBtn={campoValue} listItens={['Todos','ID','Barras','Nome','Grupo','NCM','Município', 'CEST','Tributação Estadual','Tributação Federal', 'Referência','Localização', 'Unidade','Preço Rev.','Preço Venda','Custo médio', 'Preço Custo','Quantidade','Qtd. disponível', 'Qtd. bloqueada']} onSelect={(item) => handleMenuItemClick(item)}/>
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
                              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-700 text-center">{data.Grupo?.nome}</td>
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
