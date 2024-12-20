import React, { useState, useEffect} from 'react';
import { useNavigate } from 'react-router-dom';
import Sidebar from '../../partials/Sidebar';
import Header from '../../partials/Header';
import axios from 'axios';
import DropDown from '../../components/DropDown';
import InputWBtn from '../../components/InputWBtn';

const ServiçoAdd = () => {

  const [UnSelected, setUnSelected] = useState("");
  const [atividadeSelected, setAtvSelected] = useState('');
  const [grupoModal, setGrupoModal]= useState([]);
  const [sidebarOpen, setSidebarOpen] = useState(false);

  const [formData, setFormData] = useState({
    codigo: '',
    nome: '',
    ativo: true,
    un: '',
    atividade: '',
    grupo: '',
    descricao: '',
    custo: '',
    precoVenda: '',
    precoRevenda: '',
    tribMunicipal: '',
    tribFederal: '',
    Observacoes: '',
  });
  
  const dropGrupo = [
    'Grupo A',
    'Grupo B',
    'Grupo C',
  ];
  
  const dropUn = ["AM","AMPOLA","AR","BALDE","BANDEJ","BARRA","BISNAG","BLOCO","CART","CD","CENTO","CJ","CM","CM2","CX","DISP","DZ","FARDO","FRASCO","GAL","GALÃO","GF","GR","HR","JOGO","K","KG","KIT","LATAO","LT","M2","M3","MC","MI","MILHEI","ML","MT","ND","PACOTE","PARES","PC","RESMA","ROLO","SACO","SACOLA","ST","TAMBOR","TON","TUBO","UN"];

  const dropAtv = [
    "programação", "Processamento de dados", "Analise e desenvolvimento de sistemas",
  ]
  
  useEffect(() => {
    // Função para pegar os dados da API
    const fetchData = async () => {
      try {
        const response = await axios.get('http://localhost:8080/grupos-prod-serv');

        let gruposTransformados = response.data.map(item => ({
          codigo: item.id,
          nome: item.nome
        }));
  
        setGrupoModal(gruposTransformados);
  
      } catch (error) {
        console.error('Erro ao buscar os dados', error);
      }
    };
  
    fetchData();
  }, []);

  {/* Post ADD Tabela */}
  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
        const response = await axios.post("http://localhost:8080/empresas-proprietarias/", formData);
        setFormData({
            tipo: '',
            razaoSocial: '',
            nomeFantasia: '',
            cpfCnpj: '',
            cep: '',
            nomeExibicao: '',
            municipio: '',
            uf: '',
            bairro: '',
            logradouro: '',
            numero: '',
            complemento: '',
            email: '',
            telefone: '',
            celular: '',
            contato: '',
            rgInscricaoEstadual: '',
            inscricaoEstadualMunicipal: '',
            observacao: '',
            ativo: true,
        });
    } catch (error) {
        console.error('Erro ao cadastrar empresa:', error.response ? error.response.data : error.message);
    }
  };

  {/*Itens do Menu */}
  const handleMenuItemClick = (index, item) => {
    console.log("item:",item);
    console.log("index:",index);

    if (index == "UN"){
      setUnSelected(item);
      console.log("UnSelected:"+UnSelected);
      formData.un = (item);
    }else if( index == "Grupo"){
      console.log(item);
      formData.grupo = (item);
    }else if( index == "Atividade"){
      setAtvSelected(item);
      formData.atividade = (item);
    }
  };

  {/* Redireciona para Empresas */}
  const handleRedirect = () => {
    navigate('/cadastro/servicos');
  };
  const navigate = useNavigate();

  const handleInputChange = (e) => {
    const { name, value } = e.target;

    setFormData((prevData) => ({ ...prevData, [name]: value }));
  };

  

  {/* Tela principal do administrador */}
  return (
    <div className="flex h-screen overflow-hidden">

      {/* Sidebar */}
      <Sidebar sidebarOpen={sidebarOpen} setSidebarOpen={setSidebarOpen} />

        {/* Conteúdo */}
        <div className="relative flex flex-col flex-1 overflow-y-auto overflow-x-hidden">
          <Header sidebarOpen={sidebarOpen} setSidebarOpen={setSidebarOpen} />
          <div className="flex justify-center w-full">
            <form onSubmit={handleSubmit} className="space-y-5 mx-[2rem] max-w-full  h-[65rem]">
              <h3 className="text-lg font-semibold justify-center text-center mb-4 mt-4 ml-1">Cadastro de Serviço</h3>

              {/* Dados do produto */}
              <h2 style={{ color: '#5E16ED', fontSize: '170%', fontWeight: 'bold' }}>
                Dados
                <hr style={{ border: '1px solid #5E16ED' }} />
              </h2>

              <div className="flex flex-col md:flex-row gap-4 justify-between">
                <div className="flex flex-col">
                <label className="block ml-1 text-sm font-medium leading-6 text-black">Código de barras</label>
                  <input
                    type="text"
                    name="codigoBarras"
                    value={formData.codigo}
                    onChange={handleInputChange}
                    className=" w-[13rem] h-11 px-3 py-2 rounded-md  ring-inset focus:ring-2 focus:ring-indigo-600"
                    required
                    style={{ textTransform: 'uppercase' }}
                  />
                </div>
                <div className="flex items-center">
                  <input
                    type="checkbox"
                    name="ativo"
                    checked={formData.ativo}
                    onChange={() => setFormData({ ...formData, ativo: !formData.ativo })}
                    className="mr-2 rounded"
                    
                  />
                  <label className="text-base">Ativo</label>
                </div>
                
              </div>
              
              <div className="flex flex-col md:flex-row gap-4">
              <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Nome</label>
                  <input
                    type="text"
                    name="nome"
                    value={formData.nome}
                    onChange={handleInputChange}
                    className=" w-[66rem] h-11 px-3 py-2 rounded-md  ring-inset focus:ring-2 focus:ring-indigo-600"
                    required
                    style={{ textTransform: 'uppercase' }}
                  />
                </div>
              </div>

              <div className="flex flex-col md:flex-row gap-4 justify-between">
                <div className="flex flex-col">
                  <DropDown labelDrop="UN - Unid. Saída" title={"Selecione a unidade"} ValorBtn={UnSelected} listItens={dropUn} onSelect={(item) => handleMenuItemClick('UN',item)}/>
                </div>
                <div className="flex flex-col">
                  <DropDown labelDrop="Atividade" title={"Selecione a atividade"} ValorBtn={atividadeSelected} listItens={dropAtv} onSelect={(item) => handleMenuItemClick('Atividade',item)}/>
                </div>
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Grupo</label>
                  <InputWBtn widthValue={16} heightValue={2.75} options={grupoModal} modalTitle="Escolha o grupo" onSelect={handleMenuItemClick} tipo={"Grupo"} valueSelect={1}/>
                </div>
              </div>

              <div className="flex flex-col md:flex-row gap-4">
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Descrição</label>
                  <textarea
                          type="text"
                          name="descricao"
                          value={formData.descricao}
                          onChange={handleInputChange}
                          className="w-[66rem] h-[45px] resize-none px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600"
                          style={{ textTransform: 'uppercase' }}
                      />
                </div>
              </div>

              <h2 style={{ color: '#5E16ED', fontSize: '170%', fontWeight: 'bold' }}>
                Valores
                <hr style={{ border: '1px solid #5E16ED' }} />
              </h2>

              {/* Aréa do Custo*/}
              <div className="flex flex-col md:flex-row gap-4">
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Custo</label>
                  <input
                    type="number"
                    name="custo"
                    value={formData.custo}
                    onChange={handleInputChange}
                    className="w-[21.2rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                    style={{ textTransform: 'uppercase' }}
                  />
                </div>
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Preço de venda</label>
                  <input
                    type="number"
                    name="precoVenda"
                    value={formData.precoVenda}
                    onChange={handleInputChange}
                    className="w-[21.3rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                    style={{ textTransform: 'uppercase' }}
                  />
                </div>
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Preço de revenda</label>
                  <input
                    type="number"
                    name="precoRevenda"
                    value={formData.precoRevenda}
                    onChange={handleInputChange}
                    className="w-[21.3rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                    style={{ textTransform: 'uppercase' }}
                  />
                </div>
              </div>

              <h2 style={{ color: '#5E16ED', fontSize: '170%', fontWeight: 'bold' }}>
                Tributação
                <hr style={{ border: '1px solid #5E16ED' }} />
              </h2>
              
              {/* Trib. Estadual*/}
              <div className="flex flex-col md:flex-row gap-4">
              <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Trib. Municipal</label>
                  <input
                    type="text"
                    name="tribMunicipal"
                    value={formData.tribMunicipal}
                    onChange={handleInputChange}
                    className="w-[66rem] px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600"
                    style={{ textTransform: 'uppercase' }}
                  />
                </div>
              </div>

              {/* Trib. Federal*/}
              <div className="flex flex-col md:flex-row gap-4">
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Trib. Federal</label>
                  <input
                    type="text"
                    name="tribFederal"
                    value={formData.tribFederal}
                    onChange={handleInputChange}
                    className="w-[66rem] px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600"
                    style={{ textTransform: 'uppercase' }}
                  />
                </div>
              </div>

              <div className="flex justify-end gap-4">
                <button type="submit" className="h-[3rem] w-40 px-4 py-2 rounded-md bg-indigo-600 text-white hover:bg-indigo-700">
                  Cadastrar
                </button>
                <button type="button" className="h-[3rem] w-[10rem] rounded-mdbg-white px-4 py-2 text-sm font-semibold text-gray-900 shadow-sm rounded-md ring-1 ring-inset ring-gray-300 hover:bg-gray-50">
                  Copiar
                </button>
                <button type="button" onClick={handleRedirect} className="h-[3rem] w-[10rem] rounded-mdbg-white px-4 py-2 text-sm font-semibold text-gray-900 shadow-sm rounded-md ring-1 ring-inset ring-gray-300 hover:bg-gray-50">
                  Voltar
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
    );
  }

export default ServiçoAdd;