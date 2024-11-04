import React from 'react';
import { Bar } from 'react-chartjs-2';

function DashboardCard04({itens = []}) {

  const data = {
    labels: itens.map((item) => item.nome),
    datasets: [
        {
            label: 'Vendas',
            data: itens.map((item) => item.valor),
            fill: false,
            backgroundColor: 'rgba(93,23,235,0.8)',
            borderColor: 'rgba(93,23,235,0.8)',
            barThickness: 35,
        },
    ],
  };

  return (
    <div className="flex flex-col col-span-full sm:col-span-6 bg-white dark:bg-gray-800 shadow-sm rounded-xl">
      <header className="px-5 py-4 border-b border-gray-100 dark:border-gray-700/60">
        <h2 className="font-semibold text-gray-800 dark:text-gray-100">Formas de pagamento</h2>
      </header>
      {/* Chart built with Chart.js 3 */}
      {/* Change the height attribute to adjust the chart height */}
      <Bar data={data} />
    </div>
  );
}

export default DashboardCard04;
