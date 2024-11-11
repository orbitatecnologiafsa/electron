import React from 'react';
import DoughnutChart from '../../charts/DoughnutChart';
import { tailwindConfig } from '../../utils/Utils';

function DashboardCard06() {

  const chartData = {
    labels: ['Pix', 'Dinheiro', 'Crédito', 'Débito', 'Boleto', 'Outro'],
    datasets: [
      {
        label: 'Formas de pagamento',
        data: [
          35, 30, 3,
        ],
        backgroundColor: [
          tailwindConfig().theme.colors.violet[500],
          tailwindConfig().theme.colors.sky[500],
          tailwindConfig().theme.colors.violet[860],
          
        ],
        hoverBackgroundColor: [
          tailwindConfig().theme.colors.violet[600],
          tailwindConfig().theme.colors.sky[600],
          tailwindConfig().theme.colors.violet[900],
        ],
        borderWidth: 0,
      },
    ],
  };

  return (
    <div className="flex flex-col col-span-full sm:col-span-6 bg-white dark:bg-gray-800 shadow-sm rounded-xl">
      <header className="px-5 py-4 border-b border-gray-100 dark:border-gray-700/60">
        <h2 className="font-semibold text-gray-800 dark:text-gray-100">Formas de pagamento</h2>
      </header>
      <DoughnutChart data={chartData} width={389} height={260} />
    </div>
  );
}

export default DashboardCard06;
