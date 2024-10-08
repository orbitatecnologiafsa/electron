import React, { useState } from 'react';

import Sidebar from '../../partials/Sidebar';
import Header from '../../partials/Header';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUser } from '@fortawesome/free-solid-svg-icons';
import { faSort } from '@fortawesome/free-solid-svg-icons';

function Produtos() {

  const [sidebarOpen, setSidebarOpen] = useState(false);

  {/* Tela principal do administrador */}
  return (
    <div className="flex h-screen overflow-hidden">

      {/* Sidebar */}
      <Sidebar sidebarOpen={sidebarOpen} setSidebarOpen={setSidebarOpen} />

      {/* Conteúdo */}
      <div className="relative flex flex-col flex-1 overflow-y-auto overflow-x-hidden">
        {/*  Site header */}
        <Header sidebarOpen={sidebarOpen} setSidebarOpen={setSidebarOpen} />
        <form>
          <div className="space-y-12 mt-10 ml-10">
            <div className="border-b border-gray-900/10 pb-12">
              <h2 className="text-base font-semibold leading-7 text-gray-900">Listagem de Clientes</h2>

              <div className="mt-10 grid grid-cols-1 gap-x-6 gap-y-8 sm:grid-cols-6">
                <div className="sm:col-span-4">
                  <div className="mt-2 flex space-x-4">
                    <div className="flex-1">
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
                    </div>

                    <div className="w-1/5">
                      <label htmlFor="input2" className="block text-sm font-medium leading-6 text-gray-900">Campo</label>
                      <div className="flex rounded-md shadow-sm ring-1 ring-inset ring-gray-300 focus-within:ring-2 focus-within:ring-inset focus-within:ring-indigo-600 sm:max-w-md">
                        <span className="flex select-none items-center pl-3 text-gray-500 sm:text-sm"></span>
                        <input
                          type="text"
                          id="input2"
                          className="block w-full ml-3 rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                        />
                      </div>
                    </div>
                  </div>
                  {/* Tabela */}
                    <div class="table w-full mt-5">
                      <div class="table-header-group">
                        {/* Colunas */}
                        <div class="table-row">
                          <div class="table-cell text-left ...">Código <span><FontAwesomeIcon icon={faSort}/></span></div>
                          <div class="table-cell text-left ...">Barras <span><FontAwesomeIcon icon={faSort}/></span></div>
                          <div class="table-cell text-left ...">Nome <span><FontAwesomeIcon icon={faSort}/></span></div>
                          <div class="table-cell text-left ...">UN <span><FontAwesomeIcon icon={faSort}/></span></div>
                          <div class="table-cell text-left ...">Quantidade <span><FontAwesomeIcon icon={faSort}/></span></div>
                          <div class="table-cell text-left ...">Quant Bloqueada <span><FontAwesomeIcon icon={faSort}/></span></div>
                          <div class="table-cell text-left ...">Quant Disponível <span><FontAwesomeIcon icon={faSort}/></span></div>
                          <div class="table-cell text-left ...">Quant ideal <span><FontAwesomeIcon icon={faSort}/></span></div>
                          <div class="table-cell text-left ...">Quant Ped Venda <span><FontAwesomeIcon icon={faSort}/></span></div>
                          <div class="table-cell text-left ...">Preço custo <span><FontAwesomeIcon icon={faSort}/></span></div>
                          <div class="table-cell text-left ...">Custo Médio <span><FontAwesomeIcon icon={faSort}/></span></div>
                          <div class="table-cell text-left ...">Preço Venda <span><FontAwesomeIcon icon={faSort}/></span></div>
                          <div class="table-cell text-left ...">Preço Revenda <span><FontAwesomeIcon icon={faSort}/></span></div>
                          <div class="table-cell text-left ...">Descrição <span><FontAwesomeIcon icon={faSort}/></span></div>
                          <div class="table-cell text-left ...">Contr. Lote <span><FontAwesomeIcon icon={faSort}/></span></div>
                          <div class="table-cell text-left ...">Contr. Serial <span><FontAwesomeIcon icon={faSort}/></span></div>
                          <div class="table-cell text-left ...">Contr. Grade <span><FontAwesomeIcon icon={faSort}/></span></div>
                          <div class="table-cell text-left ...">Grupo <span><FontAwesomeIcon icon={faSort}/></span></div>
                          <div class="table-cell text-left ...">NCM <span><FontAwesomeIcon icon={faSort}/></span></div>
                          <div class="table-cell text-left ...">CEST <span><FontAwesomeIcon icon={faSort}/></span></div>
                          <div class="table-cell text-left ...">Trib.Estadual <span><FontAwesomeIcon icon={faSort}/></span></div>
                          <div class="table-cell text-left ...">Trib.Federal <span><FontAwesomeIcon icon={faSort}/></span></div>
                          <div class="table-cell text-left ...">Referência <span><FontAwesomeIcon icon={faSort}/></span></div>
                          <div class="table-cell text-left ...">Ativo <span><FontAwesomeIcon icon={faSort}/></span></div>
                        </div>
                      </div>
                    </div>
                </div>
              </div>
            </div>
          </div>
        </form>
      </div>
    </div>
  );
}

export default Produtos;