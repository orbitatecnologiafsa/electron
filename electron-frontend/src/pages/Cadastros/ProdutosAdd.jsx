import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Sidebar from '../../partials/Sidebar';
import Header from '../../partials/Header';
import axios from 'axios';
import DropDown from '../../components/DropDown';
import InputWBtn from '../../components/InputWBtn';


function ProdutosAdd() {

  const [docDigitado, setDocDigitado] = useState('');

  const [documentoValue, setDocumentoValue] = useState('');
  const [cep, setCep] = useState('');
  const [formData, setFormData] = useState({
    index: "",
    codigo: '',
    nome: '',
    quantidade: '',
    classificacao: '',
    unEntrada: '',
    unSaida: '',
    UnTributacao: '',
    tControle: '',
    bloqueada: '',
    disponivel: '',
    precoCusto: '',
    custoMedio: '',
    precoVenda: '',
    precoRevenda: '',
    descricao: '',
    grupo: '',
    ncm: '',
    cest: '',
    tribEstadual: '',
    tribFederal: '',
  });
  const [UnSelected, setUnSelected] = useState("");
  const [Classified, setClassified] = useState("");
  const [UnEntrada, setUnEntrada] = useState("");
  const [UnSaida, setUnSaida] = useState("");
  const [UnTributacao, setUnTributacao] = useState("");
  const [TControle, setTControle] = useState("");

  const [grupoModal, setGrupoModal]= useState([{codigo:'1',name_fantasia:'Grupo A'},{codigo:'2',name_fantasia:'Grupo B'}]);
  const [cestModal, setCestModal]= useState([{codigo:'1',tributo_cest_codigo:'12345678'},
    {codigo:'2',tributo_cest_codigo:'12349999'}]);

  const handleMenuItemClick = (item, index) => {

    if (index == "UN"){

      setUnSelected(item);
      if(item == "KILOGRAMA"){
        formData.un = ("KG_KILOGRAMA");
      }else if(item == "LITRO"){
        formData.un = ("LT_LITRO");
      }else{
        formData.un = ("MT_METRO");
      }

    }else if( index == "Classficacao"){

      setClassified(item);
      formData.classificacao = (item);

    }else if( index == "UnEntrada"){

      setUnEntrada(item);
      formData.unEntrada = (item);

    }else if( index == "UnSaída"){

      setUnSaida(item);
      formData.unSaida = (item);
      
    }else if(index == 'UnTributacao'){

      setUnTributacao(item);
      formData.UnTributacao = (item);

    }else if(index == 'TControle'){

      setTControle(item);
      if(item == 'Controla grade'){

        formData.tControle = ("Controla_Grade");

      }else if(item == 'Vende Francionado'){

        formData.tControle = ("Vende_Fracionado");
      }else{

        formData.tControle = (item);
        
      }
    }
  }

  const [sidebarOpen, setSidebarOpen] = useState(false);
  {/* Post ADD Tabela */}
  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
        const response = await axios.post("http://localhost:8080/produtos", formData);
        setFormData({
          index: "",
          codigo: '',
          nome: '',
          quantidade: '',
          classificacao: '',
          unEntrada: '',
          unSaida: '',
          UnTributacao: '',
          tControle: '',
          bloqueada: '',
          disponivel: '',
          precoCusto: '',
          custoMedio: '',
          precoVenda: '',
          precoRevenda: '',
          descricao: '',
          grupo: '',
          ncm: '',
          cest: '',
          tribEstadual: '',
          tribFederal: '',
        });
    } catch (error) {
        console.log(formData);
        console.error('Erro ao cadastrar produtos:', error.response ? error.response.data : error.message);
    }
  };

  {/* Redireciona para Empresas */}
  const handleRedirect = () => {
    navigate('/cadastro/produtos');
  };
  const navigate = useNavigate();

  useEffect(() => {
    // Função para pegar os dados da API
    const fetchData = async () => {
      try {
        const response = await axios.get('http://localhost:8080/grupos-prod-serv');
        console.log("Antes:",response.data);

        const gruposTransformados = response.data.map(item => ({
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

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => {
      return { ...prevData, [name]: value };
    });
  };

  const onSelectedOptionModal = (tipo,option) => {
    console.log("Option:",option);
    console.log("tipo:",tipo);

    if(tipo=='Grupo'){
      formData.grupo = option;
      console.log("Grupo:",formData.grupo);
    }else if(tipo=='Cest'){
      formData.cest = option;
    }else if(tipo == 'Produto'){
      formData.produto = option;
    }
  }

  const unidadesEmbList = ["KILOGRAMA", "LITRO", "METRO"];

  const ClassificacaoList = ["Despesa", "Imobilizado", "Produto"];

  const unidadeSaidaEntradaTributação = ["AM","AMPOLA","AR","BALDE","BANDEJ","BARRA","BISNAG","BLOCO","CART","CD","CENTO","CJ","CM","CM2","CX","DISP","DZ","FARDO","FRASCO","GAL","GALÃO","GF","GR","HR","JOGO","K","KG","KIT","LATAO","LT","M2","M3","MC","MI","MILHEI","ML","MT","ND","PACOTE","PARES","PC","RESMA","ROLO","SACO","SACOLA","ST","TAMBOR","TON","TUBO","UN"];

  const tipoControle = [ "Composição", "Controla grade", "Pesável","Vende Francionado"];

  {/* Tela principal do administrador */}
  return (
    <div className="flex h-screen overflow-hidden">

      {/* Sidebar */}
      <Sidebar sidebarOpen={sidebarOpen} setSidebarOpen={setSidebarOpen} />

        {/* Conteúdo */}
        <div className="relative flex flex-col flex-1 overflow-y-auto overflow-x-hidden">
          <Header sidebarOpen={sidebarOpen} setSidebarOpen={setSidebarOpen} />
          <div className="flex justify-center w-full">
            <form onSubmit={handleSubmit} className="space-y-5 mx-[2rem] max-w-full  h-[85rem]">
              <h3 className="text-lg font-semibold justify-center text-center mb-4 mt-4 ml-1">Cadastro de Produto</h3>

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

              {/* Dados do produto */}
              <h2 style={{ color: '#5E16ED', fontSize: '170%', fontWeight: 'bold' }}>
                Dados
                <hr style={{ border: '1px solid #5E16ED' }} />
              </h2>

              <div className="flex flex-col md:flex-row gap-4">
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Código de barras</label>
                  <input
                    type="text"
                    name="codigo"
                    value={formData.codigo}
                    onChange={handleInputChange}
                    className=" w-[13rem] h-11 px-3 py-2 rounded-md  ring-inset focus:ring-2 focus:ring-indigo-600"
                    required
                  />
                </div>
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Nome</label>
                  <input
                    type="text"
                    name="nome"
                    value={formData.nome}
                    onChange={handleInputChange}
                    className=" w-[23rem] h-11 px-3 py-2 rounded-md  ring-inset focus:ring-2 focus:ring-indigo-600"
                    required
                  />
                </div>
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Grupo</label>
                  <InputWBtn widthValue={8.5} options={grupoModal} modalTitle="Escolha o grupo" onSelect={onSelectedOptionModal} tipo={"Grupo"}/>
                </div>

                <div className="flex flex-col">
                  <DropDown labelDrop={"Classificação"} title={"Selecione a classificação"} ValorBtn={Classified} listItens={ClassificacaoList} onSelect={(item) => handleMenuItemClick(item,"Classficacao")}/>
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
                    />
              </div>
              </div>

              <div className="flex flex-col md:flex-row gap-4 justify-between ">

                <div className="flex flex-col">
                  <DropDown labelDrop={"Tipo de controle"} title={"Selecione o tipo de controle"} ValorBtn={TControle} listItens={tipoControle} onSelect={(item) => handleMenuItemClick(item,"TControle")}/>
                </div>

                <div className="flex flex-col">
                  <DropDown labelDrop={"Unidade de Entrada"} title={"Selecione a Un. entrada"} ValorBtn={UnEntrada} listItens={unidadeSaidaEntradaTributação} onSelect={(item) => handleMenuItemClick(item,"UnEntrada")}/>
                </div>

                <div className="flex flex-col">
                  <DropDown labelDrop={"Unidade de Saída"} title={"Selecione a Un. saída"} ValorBtn={UnSaida} listItens={unidadeSaidaEntradaTributação} onSelect={(item) => handleMenuItemClick(item,"UnSaída")}/>
                </div>

                <div className="flex flex-col">
                  <DropDown labelDrop={"Unidade de Tributação"} title={"Selecione a Un. Tributação"} ValorBtn={UnTributacao} listItens={unidadeSaidaEntradaTributação} onSelect={(item) => handleMenuItemClick(item,"UnTributacao")}/>
                </div>
              </div>
              
              <h2 style={{ color: '#5E16ED', fontSize: '170%', fontWeight: 'bold' }}>
                Quantidade
                <hr style={{ border: '1px solid #5E16ED' }} />
              </h2>

              {/* Aréa da Quantidade*/}
              <div className="flex flex-col md:flex-row gap-4  justify-between">
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Quantidade</label>
                  <input
                    type="text"
                    name="quantidade"
                    value={formData.quantidade}
                    onChange={handleInputChange}
                    maxLength={documentoValue === 'CPF' ? 14 : 18}
                    className="w-[32.5rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                  />
                </div>
                <div className="flex flex-col">
                   <DropDown labelDrop={"Unidade de Medida"} title={"Selecione a unidade"} ValorBtn={UnSelected} listItens={unidadesEmbList} onSelect={(item) => handleMenuItemClick(item,"UN")}/>

                </div>
              </div>

              {/* Aréa do Qauntidade 2*/}
              <div className="flex flex-col md:flex-row gap-4">
              <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Quantidade bloqueada</label>
                  <input
                    type="text"
                    name="bloqueada"
                    value={formData.bloqueada}
                    onChange={handleInputChange}
                    className="w-[32.5rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                  />
                </div>
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Quantidade disponível</label>
                  <input
                    type="text"
                    name="disponivel"
                    value={formData.disponivel}
                    onChange={handleInputChange}
                    className="w-[32.5rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
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
                    type="text"
                    name="custo"
                    value={formData.custo}
                    onChange={handleInputChange}
                    className="w-[32.5rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                  />
                </div>
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Custo médio</label>
                  <input
                    type="text"
                    name="custoMedio"
                    value={formData.custoMedio}
                    onChange={handleInputChange}
                    className="w-[32.5rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                  />
                </div>
              </div>
              
              {/* Aréa do Preço*/}
              <div className="flex flex-col md:flex-row gap-4">
              <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Preço de venda</label>
                  <input
                    type="text"
                    name="precoVenda"
                    value={formData.precoVenda}
                    onChange={handleInputChange}
                    className="w-[32.5rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                  />
                </div>
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Preço de revenda</label>
                  <input
                    type="text"
                    name="precoRevenda"
                    value={formData.precoRevenda}
                    onChange={handleInputChange}
                    className="w-[32.5rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                  />
                </div>
              </div>

              <h2 style={{ color: '#5E16ED', fontSize: '170%', fontWeight: 'bold' }}>
                Tributação
                <hr style={{ border: '1px solid #5E16ED' }} />
              </h2>

              {/* Contato do Cliente */}
              <div className="flex flex-col md:flex-row gap-4">
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">NCM</label>
                  <input
                    type="text"
                    name="ncm"
                    value={formData.ncm}
                    onChange={handleInputChange}
                    className="w-[44rem] px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600"
                  />
                </div>
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">CEST</label>
                  <input
                    type="text"
                    name="cest"
                    value={formData.cest}
                    onChange={handleInputChange}
                    className="w-[21rem] px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600"
                  />
                  <InputWBtn widthValue={18} options={grupoModal} modalTitle="Escolha o Cest" onSelect={onSelectedOptionModal} tipo={"Cest"}/>
                </div>
              </div>
              
              {/* Trib. Estadual*/}
              <div className="flex flex-col md:flex-row gap-4">
              <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Trib. Estadual</label>
                  <input
                    type="text"
                    name="tribEstadual"
                    value={formData.tribEstadual}
                    onChange={handleInputChange}
                    className="w-[66rem] px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600"
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

export default ProdutosAdd;