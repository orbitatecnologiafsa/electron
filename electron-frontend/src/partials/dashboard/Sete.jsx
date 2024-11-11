import React from 'react';
import { Bar, Line } from 'react-chartjs-2';

function DashboardCard05Sete({ titulo, itens = [], graficoTipo = 'bar' }) {

  const formatDate = (dateString) => {
    const [day, month, year] = dateString.split('-');
    const monthNames = [
      "janeiro", "fevereiro", "março", "abril", "maio", 
      "junho", "julho", "agosto", "setembro", "outubro", 
      "novembro", "dezembro"
    ];
    return `${day} ${monthNames[parseInt(month) - 1]}`;
  };

  const data = {
    labels: itens.map((item) => formatDate(item.data)),
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

  const options = {
    scales: {
      x: {
        grid: {
          display: false,
        },
      },
      y: {
        beginAtZero: true,
      },
    },
  };

  // Renderiza o gráfico correto com base no parâmetro "graficoTipo"
  const renderChart = () => {
    switch (graficoTipo) {
      case 'line':
        return <Line data={data} options={options} />;
      case 'bar':
      default:
        return <Bar data={data} options={options} />;
    }
  };

  return (
    <div className="flex flex-col col-span-full sm:col-span-6 bg-white dark:bg-gray-800 shadow-sm rounded-xl">
      <header className="px-5 py-4 border-b border-gray-100 dark:border-gray-700/60">
        <h2 className="font-semibold text-gray-800 dark:text-gray-100">{titulo}</h2>
      </header>
      {renderChart()}
    </div>
  );
}

export default DashboardCard05Sete;
