import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Sidebar from '../../partials/Sidebar';
import Header from '../../partials/Header';
import axios from 'axios';
import DropDown from '../../components/DropDown';
import InputWBtn from '../../components/InputWBtn';
import Datepicker from '../../components/Datepicker';
import InputImgBtn from '../../components/InputImgBtn';

function ProdutosAdd() {

  const [documentoValue, setDocumentoValue] = useState('');
  const [formData, setFormData] = useState({
    codigo: '',
    ativo: true,
    nome: '',
    quantidade: '',
    classificacao: '',
    unidadeEntrada: '',
    unidadeSaida: '',
    UnTributacao: '',
    fatorConversaoId: '',
    tControle: '',
    bloqueado: '',
    disponivel: '',
    precoCusto: '',
    precoCustoMedio: '',
    precoVenda: '',
    precoRevenda: '',
    descricao: '',
    grupoProdServId: '',
    codigoCestNcmId: '',
    TributoCest : '',
    codigoAnpId: '',
    tributacaoEstadualId: '',
    tributacaoFederalId: '',
    diasValidade: '',
  });
  const [UnSelected, setUnSelected] = useState("");
  const [Classified, setClassified] = useState("");
  const [UnEntrada, setUnEntrada] = useState("");
  const [UnSaida, setUnSaida] = useState("");
  const [UnTributacao, setUnTributacao] = useState("");
  const [TControle, setTControle] = useState("");

  const [grupoModal, setGrupoModal]= useState([]);
  const [ncmModal, setNcmModal]= useState([{codigo:'1',tributo_cest_codigo:'12345678'},{codigo:'2',tributo_cest_codigo:'12349999'}]);
  const [tribEstadualModal, setTribEstadualModal]= useState([{codigo:'13',nome:'Nome da Tributação Estadual Exemplo'}]);
  const [tribFederalModal, setTribFederalModal]= useState([{codigo:'1',nome:'Exemplo1'}]);

  const [anpModal, setAnpModal]= useState([{codigo:'10101',name_fantasia:'Grupo A'},{codigo:'20202',name_fantasia:'Grupo B'}]);
  const [fatorConversao, setFatorConversao]= useState([{codigo:'5',fator_cenversao_codigo:'A'}]);
  const [codigo,setCodigo] = useState(0);
  
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
      console.log(item);
      formData.unidadeEntrada = (item);

    }else if( index == "UnSaída"){

      setUnSaida(item);
      formData.unidadeSaida = (item);
      
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

    if (!formData.unidadeEntrada || !formData.unidadeSaida || !formData.grupoProdServId) {
      alert("Por favor, preencha todos os campos obrigatórios!");
      return;
    }

    try {
      console.log("Formes antes de enviar: ", formData);

        const response = await axios.post("http://localhost:8080/produtos", formData);
        setFormData({
          codigo: '',
          ativo: true,
          nome: '',
          quantidade: '',
          classificacao: '',
          unidadeEntrada: '',
          unidadeSaida: '',
          UnTributacao: '',
          fatorConversaoId: '',
          tControle: '',
          bloqueado: '',
          disponivel: '',
          precoCusto: '',
          precoCustoMedio: '',
          precoVenda: '',
          precoRevenda: '',
          descricao: '',
          grupoProdServId: '',
          codigoCestNcmId: '',
          TributoCest : '',
          codigoAnpId: '',
          tributacaoEstadualId: '',
          tributacaoFederalId: '',
          diasValidade: '',
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

        let gruposTransformados = response.data.map(item => ({
          codigo: item.id,
          nome: item.nome
        }));

        const responsep = await axios.get('http://localhost:8080/produtos');
        setCodigo(responsep.data.length);

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
      formData.grupoProdServId = option;
      console.log("Grupo:",formData.grupoProdServId);
    }else if(tipo=='Cest'){
      formData.TributoCest  = option;
      console.log("Cest:",formData.TributoCest );
    }else if(tipo == 'Produto'){
      formData.produto = option;
      console.log("Produto:",formData.produto);
    }else if(tipo == 'ncm'){
      formData.codigoCestNcmId = option;
      console.log("Ncm:",formData.codigoCestNcmId);
    }else if(tipo == 'Trib. Estadual'){
      formData.tributacaoEstadualId = option;
      console.log("Trib. Estadual:",formData.tributacaoEstadualId);
    } else if(tipo == 'Trib. Federal'){
      formData.tributacaoFederalId = option;
      console.log("Trib. Federal:",formData.tributacaoFederalId);
    } else if(tipo == 'Anp'){
      formData.codigoAnpId = option;
    } else if(tipo == 'FConversao'){
      formData.fatorConversaoId = option;
    }else if(tipo == 'Validade'){
      formData.diasValidade = dates[0].toLocaleDateString('pt-BR');
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

              {/* Dados do produto */}
              <h2 style={{ color: '#5E16ED', fontSize: '170%', fontWeight: 'bold' }}>
                Dados
                <hr style={{ border: '1px solid #5E16ED' }} />
              </h2>

              <div className="flex flex-col md:flex-row gap-4 justify-between">
                <div className='flex flex-col'>
                  <InputImgBtn/>
                </div>
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Código</label>
                  <input
                    type="text"
                    name="codigo"
                    value={codigo}
                    className=" w-[13rem] h-11 px-3 py-2 rounded-md  ring-inset focus:ring-2 focus:ring-indigo-600"
                    required
                    readOnly
                    style={{ textTransform: 'uppercase' }}
                  />
                </div>

                <div className="flex flex-col">
                  <DropDown labelDrop={"Classificação"} title={"Selecione a classificação"} ValorBtn={Classified} listItens={ClassificacaoList} onSelect={(item) => handleMenuItemClick(item,"Classficacao")}/>
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

              <div className="flex flex-col md:flex-row gap-4 justify-between">
                
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Código de barras</label>
                  <input
                    type="text"
                    name="codigo"
                    value={formData.codigo}
                    onChange={handleInputChange}
                    className=" w-[13rem] h-11 px-3 py-2 rounded-md  ring-inset focus:ring-2 focus:ring-indigo-600"
                    required
                    style={{ textTransform: 'uppercase' }}
                  />
                </div>

                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Nome</label>
                  <input
                    type="text"
                    name="nome"
                    value={formData.nome}
                    onChange={handleInputChange}
                    className=" w-[30rem] h-11 px-3 py-2 rounded-md  ring-inset focus:ring-2 focus:ring-indigo-600"
                    required
                    style={{ textTransform: 'uppercase' }}
                  />
                </div>

                <div className="flex flex-col">
                    <label className="block ml-1 text-sm font-medium leading-6 text-black">Data de validade</label>
                    <Datepicker align="center" onDateChange={onSelectedOptionModal} tipo={'Validade'}/>
                </div>
              </div>

              <div className="flex flex-col md:flex-row gap-4">
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Grupo</label>
                  <InputWBtn widthValue={29.5} heightValue={2.75} options={grupoModal} modalTitle="Escolha o grupo" onSelect={onSelectedOptionModal} tipo={"Grupo"} valueSelect={1}/>
                </div>

                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Fator de conversão</label>
                  <InputWBtn widthValue={29} heightValue={2.75} options={fatorConversao} modalTitle="Escolha o fator de conversão" onSelect={onSelectedOptionModal} valueSelect={1} tipo={"FConversao"}/>
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
                    type="number"
                    name="quantidade"
                    value={formData.quantidade}
                    onChange={handleInputChange}
                    maxLength={documentoValue === 'CPF' ? 14 : 18}
                    className="w-[32.5rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                    style={{ textTransform: 'uppercase' }}
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
                    type="number"
                    name="bloqueado"
                    value={formData.bloqueado}
                    onChange={handleInputChange}
                    className="w-[32.5rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                    style={{ textTransform: 'uppercase' }}
                  />
                </div>
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Quantidade disponível</label>
                  <input
                    type="number"
                    name="disponivel"
                    value={formData.disponivel}
                    onChange={handleInputChange}
                    className="w-[32.5rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
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
                    name="precoCusto"
                    value={formData.precoCusto}
                    onChange={handleInputChange}
                    className="w-[32.5rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                    style={{ textTransform: 'uppercase' }}
                  />
                </div>
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Custo médio</label>
                  <input
                    type="number"
                    name="precoCustoMedio"
                    value={formData.precoCustoMedio}
                    onChange={handleInputChange}
                    className="w-[32.5rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                    style={{ textTransform: 'uppercase' }}
                  />
                </div>
              </div>
              
              {/* Aréa do Preço*/}
              <div className="flex flex-col md:flex-row gap-4">
              <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Preço de venda</label>
                  <input
                    type="number"
                    name="precoVenda"
                    value={formData.precoVenda}
                    onChange={handleInputChange}
                    className="w-[32.5rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
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
                    className="w-[32.5rem] h-11 px-3 py-2 rounded-md ring-inset focus:ring-2 focus:ring-indigo-600 disabled:bg-gray-300"
                    style={{ textTransform: 'uppercase' }}
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
                  <InputWBtn widthValue={18} heightValue={2.75} options={ncmModal} modalTitle="Escolha o NCM" onSelect={onSelectedOptionModal} valueSelect={1} tipo={"ncm"}/>
                </div>
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Codigo Anp</label>
                  <InputWBtn widthValue={18} heightValue={2.75} options={anpModal} modalTitle="Escolha o codigo anp" onSelect={onSelectedOptionModal} valueSelect={1} tipo={"Anp"}/>
                </div>
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">CEST</label>
                  <InputWBtn widthValue={18} heightValue={2.75} options={grupoModal} modalTitle="Escolha o Cest" onSelect={onSelectedOptionModal} valueSelect={1} tipo={"Cest"}/>
                </div>
              </div>
              
              {/* Trib. Estadual*/}
              <div className="flex flex-col md:flex-row gap-4">
              <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Trib. Estadual</label>
                  <InputWBtn widthValue={63} heightValue={2.75} options={tribEstadualModal} modalTitle="Escolha o Trib. Estadual" onSelect={onSelectedOptionModal} valueSelect={1} tipo={"Trib. Estadual"}/>
                </div>
              </div>

              {/* Trib. Federal*/}
              <div className="flex flex-col md:flex-row gap-4">
                <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">Trib. Federal</label>
                   <InputWBtn widthValue={63} heightValue={2.75} options={tribFederalModal} modalTitle="Escolha o Trib. Federal" onSelect={onSelectedOptionModal}  valueSelect={1} tipo={"Trib. Federal"}/>
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