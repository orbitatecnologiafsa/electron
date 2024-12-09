import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Sidebar from '../../../partials/Sidebar';
import Header from '../../../partials/Header';
import axios from 'axios';
import Datepicker from '../../../components/Datepicker';
import DropDown from '../../../components/DropDown';
import TabelaWBtn from '../../../components/TabelaWBtn';
import InputWBtn from '../../../components/InputWBtn';

const NotaEntradaAdd = () => {

    const [formsData,setFormsData] = useState({
        Fornecedor: '',
        emissao: '',
        trasnportadora: '',
        previsao: '',
        produto: '',
        totalPedido:'',
        desconto:'',
        NParcela: '',
        OpVcto: '',
        dia:'',
        peso:'',
        observacao: '',
      });
      
    const handleDateChange = (dates,tipo) => {
        console.log("Data: ",dates, " Tipo: ",tipo);
        if(tipo == 'Emissao'){
          formsData.emissao = dates[0].toLocaleDateString('pt-BR');
        }else if(tipo == 'Previsao'){
          formsData.previsao = dates[0].toLocaleDateString('pt-BR');
        }
      };
    
    return(
        <div className="flex h-screen overflow-hidden">

      {/* Sidebar */}
      <Sidebar sidebarOpen={sidebarOpen} setSidebarOpen={setSidebarOpen} />

        {/* Conteúdo */}
        <div className="relative flex flex-col flex-1 overflow-y-auto overflow-x-hidden">
          <Header sidebarOpen={sidebarOpen} setSidebarOpen={setSidebarOpen} />
          <div className="flex justify-center w-full">
            <form onSubmit={handleSubmit} className="space-y-5 mx-[2rem] max-w-full  h-[65rem]">
              <h3 className="text-lg font-semibold justify-center text-center mb-4 mt-4 ml-1">Adicionar Pedido</h3>

              {/* Dados do produto */}
              <h2 style={{ color: '#5E16ED', fontSize: '170%', fontWeight: 'bold' }}>
                Dados do Pedido de Compra
                <hr style={{ border: '1px solid #5E16ED' }} />
              </h2>

              <div className="flex flex-col md:flex-row gap-4 justify-between">
                <div className="flex flex-col">
                    <label className="block ml-1 text-sm font-medium leading-6 text-black">Emissão</label>
                    <Datepicker align="center" onDateChange={handleDateChange} tipo={'Emissao'}/>
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

export default NotaEntradaAdd;