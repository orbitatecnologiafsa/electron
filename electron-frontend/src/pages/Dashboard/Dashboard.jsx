//Dashboard.jsx
import React, { useState } from 'react';

import Sidebar from '../../partials/Sidebar';
import Header from '../../partials/Header';

import DashboardCard01 from '../../partials/dashboard/DashboardCard01';
import DashboardCard02 from '../../partials/dashboard/DashboardCard02';
import DashboardCard03 from '../../partials/dashboard/DashboardCard03';
import DashboardCard04 from '../../partials/dashboard/DashboardCard04';
import DashboardCard05 from '../../partials/dashboard/DashboardCard05';
import DashboardCard05Sete from '../../partials/dashboard/Sete';
import DashboardCard06 from '../../partials/dashboard/DashboardCard06';
import DashboardCard07 from '../../partials/dashboard/DashboardCard07';
import DashboardCard08 from '../../partials/dashboard/DashboardCard08';
import DashboardCard09 from '../../partials/dashboard/DashboardCard09';
import DashboardCard10 from '../../partials/dashboard/DashboardCard10';
import DashboardCard11 from '../../partials/dashboard/DashboardCard11';
import DashboardCard12 from '../../partials/dashboard/DashboardCard12';
import DashboardCard13 from '../../partials/dashboard/DashboardCard13';

function Dashboard() {

  const [sidebarOpen, setSidebarOpen] = useState(false);
  const conteudo4= [
    { nome: "Item 1", valor: 10 },
    { nome: "Item 2", valor: 20 },
    { nome: "Item 3", valor: 30 }
  ];
  const conteudo5= [
    { data: "23-05-2024", valor: 10 },
    { data: "24-05-2024", valor: 20 },
    { data: "25-05-2024", valor: 30 },
    { data: "26-05-2024", valor: 33 },
    { data: "27-05-2024", valor: 54 },
    { data: "28-05-2024", valor: 23 },
    { data: "29-05-2024", valor: 12 },
  ];

  const conteudo6 = [
    { data: "01-05-2024", valor: 15 },
    { data: "02-05-2022", valor: 18 },
    { data: "03-05-2022", valor: 22 },
    { data: "04-05-2022", valor: 25 },
    { data: "05-05-2022", valor: 30 },
    { data: "06-05-2022", valor: 35 },
    { data: "07-05-2022", valor: 40 },
    { data: "08-05-2022", valor: 45 },
    { data: "09-05-2022", valor: 50 },
    { data: "10-05-2022", valor: 55 },
    { data: "11-05-2022", valor: 60 },
    { data: "12-05-2022", valor: 65 },
    { data: "13-05-2022", valor: 70 },
    { data: "14-05-2022", valor: 5 },
    { data: "15-05-2022", valor: 8 },
    { data: "16-05-2022", valor: 85 },
    { data: "17-05-2022", valor: 9 },
    { data: "18-05-2022", valor: 95 },
    { data: "19-05-2022", valor: 0 },
    { data: "20-05-2022", valor: 105 },
    { data: "21-05-2022", valor: 10 },
    { data: "22-05-2022", valor: 115 },
    { data: "23-05-2022", valor: 20 },
    { data: "24-05-2022", valor: 125 },
    { data: "25-05-2022", valor: 130 },
    { data: "26-05-2022", valor: 135 },
    { data: "27-05-2022", valor: 14 },
    { data: "28-05-2022", valor: 15 },
    { data: "29-05-2022", valor: 160 },
    { data: "30-05-2022", valor: 155 },
    { data: "31-05-2022", valor: 120 },
];

  
  return (
    <div className="flex h-screen overflow-hidden">

      {/* Sidebar */}
      <Sidebar sidebarOpen={sidebarOpen} setSidebarOpen={setSidebarOpen} />

      {/* Content area */}
      <div className="relative flex flex-col flex-1 overflow-y-auto overflow-x-hidden">

        {/*  Site header */}
        <Header sidebarOpen={sidebarOpen} setSidebarOpen={setSidebarOpen} />

        <main className="grow">
          <div className="px-4 sm:px-6 lg:px-8 py-8 w-full max-w-9xl mx-auto">

            {/* Dashboard actions */}
            <div className="sm:flex sm:justify-between sm:items-center mb-8">

              {/* Left: Title */}
              <div className="mb-4 sm:mb-0">
                <h1 className="text-2xl md:text-3xl text-gray-800 dark:text-gray-100 font-bold">Dashboard</h1>
              </div>

              {/* Right: Actions */}
              <div className="grid grid-flow-col sm:auto-cols-max justify-start sm:justify-end gap-2">
                {/* Add view button */}
                <button className="btn bg-gray-900 text-gray-100 hover:bg-gray-800 dark:bg-gray-100 dark:text-gray-800 dark:hover:bg-white">
                  <svg className="fill-current shrink-0 xs:hidden" width="16" height="16" viewBox="0 0 16 16">
                    <path d="M15 7H9V1c0-.6-.4-1-1-1S7 .4 7 1v6H1c-.6 0-1 .4-1 1s.4 1 1 1h6v6c0 .6.4 1 1 1s1-.4 1-1V9h6c.6 0 1-.4 1-1s-.4-1-1-1z" />
                  </svg>
                  <span className="max-xs:sr-only">Add View</span>
                </button>                
              </div>

            </div>

            {/* Cards */}
            <div className="grid grid-cols-12 gap-6">
              {/* Formas de pagamento */}
              <DashboardCard04 itens={conteudo4}/>
              {/* Vendas dos últimos 7 dias*/}
              <DashboardCard05Sete titulo="Vendas dos últimos 7 dias" itens={conteudo5} graficoTipo = 'bar' />
              {/* Vendas dos últimos 30 dias*/}
              <DashboardCard05Sete titulo="Vendas dos últimos 30 dias" itens={conteudo6} graficoTipo = 'line' />
              {/* Top produtos*/}
              <DashboardCard07/>
              {/* Comissão de vendedores por mês*/}
              <DashboardCard10 />
              
            </div>

          </div>
        </main>

      </div>
    </div>
  );
}

export default Dashboard;