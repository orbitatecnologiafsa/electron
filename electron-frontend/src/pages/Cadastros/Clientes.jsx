import React, { useState } from 'react';

import Sidebar from '../../partials/Sidebar';
import Header from '../../partials/Header';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUser } from '@fortawesome/free-solid-svg-icons';
import { faSort } from '@fortawesome/free-solid-svg-icons';

function Clientes() {

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
          <div class="space-y-12 mt-10 ml-10">
            <div class="border-b border-gray-900/10 pb-12">
              <h2 class="text-base font-semibold leading-7 text-gray-900">Listagem de Clientes</h2>

              <div class="mt-10 grid grid-cols-1 gap-x-6 gap-y-8 sm:grid-cols-6">
                <div class="sm:col-span-4">
                  <label for="username" class="block text-sm font-medium leading-6 text-gray-900">Descrição</label>
                  <div class="mt-2">
                    <div class="flex rounded-md shadow-sm ring-1 ring-inset ring-gray-300 focus-within:ring-2 focus-within:ring-inset focus-within:ring-indigo-600 sm:max-w-md">
                      <span class="flex select-none items-center pl-3 text-gray-500 sm:text-sm"><FontAwesomeIcon icon={faUser} /></span>
                      <input type="text" id="descricaoCliente" class="block w-full ml-3 rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"/>
                    </div>
                  </div>
                  {/* Tabela */}
                    <div class="table w-full mt-5">
                      <div class="table-header-group">
                        {/* Colunas */}
                        <div class="table-row">
                          <div class="table-cell text-left ...">Documento <span><FontAwesomeIcon icon={faSort}/></span></div>
                          <div class="table-cell text-left ...">Nome <span><FontAwesomeIcon icon={faSort}/></span></div>
                          <div class="table-cell text-left ...">Fantasia <span><FontAwesomeIcon icon={faSort}/></span></div>
                          <div class="table-cell text-left ...">Contato <span><FontAwesomeIcon icon={faSort}/></span></div>
                          <div class="table-cell text-left ...">UF <span><FontAwesomeIcon icon={faSort}/></span></div>
                          <div class="table-cell text-left ...">Telefone <span><FontAwesomeIcon icon={faSort}/></span></div>
                          <div class="table-cell text-left ...">Celular <span><FontAwesomeIcon icon={faSort}/></span></div>
                          <div class="table-cell text-left ...">Ativo <span><FontAwesomeIcon icon={faSort}/></span></div>
                          <div class="table-cell text-left ...">Última compra <span><FontAwesomeIcon icon={faSort}/></span></div>
                          <div class="table-cell text-left ...">Data de Nascimento <span><FontAwesomeIcon icon={faSort}/></span></div>
                        </div>
                      </div>
                      <div class="table-row-group">
                        <div class="table-row">
                        <div class="table-cell text-left ...">Documento</div>
                          <div class="table-cell text-left ...">Nome</div>
                          <div class="table-cell text-left ...">Fantasia</div>
                          <div class="table-cell text-left ...">Contato</div>
                          <div class="table-cell text-left ...">UF</div>
                          <div class="table-cell text-left ...">Telefone</div>
                          <div class="table-cell text-left ...">Celular</div>
                          <div class="table-cell text-left ...">Ativo</div>
                          <div class="table-cell text-left ...">Última compra</div>
                          <div class="table-cell text-left ...">Data de Nascimento</div>
                        </div>
                        <div class="table-row">
                        <div class="table-cell text-left ...">Documento</div>
                          <div class="table-cell text-left ...">Nome</div>
                          <div class="table-cell text-left ...">Fantasia</div>
                          <div class="table-cell text-left ...">Contato</div>
                          <div class="table-cell text-left ...">UF</div>
                          <div class="table-cell text-left ...">Telefone</div>
                          <div class="table-cell text-left ...">Celular</div>
                          <div class="table-cell text-left ...">Ativo</div>
                          <div class="table-cell text-left ...">Última compra</div>
                          <div class="table-cell text-left ...">Data de Nascimento</div>
                        </div>
                        <div class="table-row">
                        <div class="table-cell text-left ...">Documento</div>
                          <div class="table-cell text-left ...">Nome</div>
                          <div class="table-cell text-left ...">Fantasia</div>
                          <div class="table-cell text-left ...">Contato</div>
                          <div class="table-cell text-left ...">UF</div>
                          <div class="table-cell text-left ...">Telefone</div>
                          <div class="table-cell text-left ...">Celular</div>
                          <div class="table-cell text-left ...">Ativo</div>
                          <div class="table-cell text-left ...">Última compra</div>
                          <div class="table-cell text-left ...">Data de Nascimento</div>
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

export default Clientes;