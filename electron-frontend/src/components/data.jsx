import React, { useState } from 'react';
import Flatpickr from 'react-flatpickr';
import "flatpickr/dist/themes/light.css"; // Importando o estilo do Flatpickr

function YearPicker({ align, onDateChange, tipo }) {
  const [selectedDate, setSelectedDate] = useState(new Date());
  const [showYearSelector, setShowYearSelector] = useState(false); // Controla a exibição do seletor de ano
  const [selectedYear, setSelectedYear] = useState(new Date().getFullYear()); // Armazena o ano selecionado

  const options = {
    mode: 'single',
    static: true,
    dateFormat: 'M j, Y', // Formato para exibir a data
    defaultDate: selectedDate,
    prevArrow: '<svg class="fill-current" width="7" height="11" viewBox="0 0 7 11"><path d="M5.4 10.8l1.4-1.4-4-4 4-4L5.4 0 0 5.4z" /></svg>',
    nextArrow: '<svg class="fill-current" width="7" height="11" viewBox="0 0 7 11"><path d="M1.4 10.8L0 9.4l4-4-4-4L1.4 0l5.4 5.4z" /></svg>',
    onReady: (selectedDates, dateStr, instance) => {
      instance.element.value = dateStr.replace('to', '-');
      const customClass = align ? align : '';
      instance.calendarContainer.classList.add(`flatpickr-${customClass}`);
    },
    onChange: (selectedDates, dateStr, instance) => {
      instance.element.value = dateStr.replace('to', '-');
      setSelectedDate(selectedDates[0]);
      if (onDateChange) {
        onDateChange(selectedDates, tipo);
      }
    },
    onYearChange: () => setShowYearSelector(true), // Ativa o seletor de ano
  };

  const years = [];
  const currentYear = new Date().getFullYear();

  // Cria um array de anos, por exemplo de 2000 até o ano atual
  for (let year = 2000; year <= currentYear; year++) {
    years.push(year);
  }

  const handleYearChange = (e) => {
    const year = parseInt(e.target.value);
    if (year) {
      setSelectedYear(year);
      setShowYearSelector(false); // Fecha o seletor de ano
      setSelectedDate(new Date(year, selectedDate.getMonth(), selectedDate.getDate())); // Atualiza a data com o ano selecionado
    }
  };

  return (
    <div className="relative flex flex-col items-center">
      {/* Exibe o calendário Flatpickr */}
      <div className="relative w-full max-w-[15.5rem]">
        <Flatpickr
          className="form-input pl-9 dark:bg-gray-800 text-black-600 hover:text-gray-800 dark:text-gray-300 dark:hover:text-gray-100 font-medium w-full"
          options={options}
        />

        {/* Ícone de calendário */}
        <div className="absolute inset-0 right-auto flex items-center pointer-events-none">
          <svg
            className="fill-current text-gray-400 dark:text-black-500 ml-3"
            width="16"
            height="25"
            viewBox="0 0 16 16"
          >
            <path d="M5 4a1 1 0 0 0 0 2h6a1 1 0 1 0 0-2H5Z" />
            <path d="M4 0a4 4 0 0 0-4 4v8a4 4 0 0 0 4 4h8a4 4 0 0 0 4-4V4a4 4 0 0 0-4-4H4ZM2 4a2 2 0 0 1 2-2h8a2 2 0 0 1 2 2v8a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V4Z" />
          </svg>
        </div>
      </div>

      {/* Customizando a cor do dia selecionado usando Tailwind CSS */}
      <style jsx>{`
        .flatpickr-day.selected {
          background-color: #5E16ED !important;
          color: white !important;
        }
        
        .flatpickr-day.selected:hover {
          background-color: #5E16ED!important; /* Alteração ao passar o mouse */
        }
      `}</style>
    </div>
  );
}

export default YearPicker;
