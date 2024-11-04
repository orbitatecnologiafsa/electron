import { Menu, MenuButton, MenuItems, MenuItem } from '@headlessui/react';

function DropDonw({labelDrop, ValorBtn, listItens = [], onSelect = (item,tipo) => {}}) {
    return(
        <div className="flex flex-col">
                  <label className="block ml-1 text-sm font-medium leading-6 text-black">{labelDrop}</label>
                  <Menu as="div" className="flex rounded-md">
                            <div>
                              <MenuButton className="w-56 rounded-md bg-white px-3 py-2 text-sm font-semibold text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 hover:bg-gray-50 h-11">
                                {ValorBtn || 'Selecione a UF'}
                              </MenuButton>
                            </div>
                            <MenuItems
                              transition
                              className="absolute z-10 mt-10 w-56 origin-top-right rounded-md bg-white shadow-lg ring-1 ring-black ring-opacity-5 transition focus:outline-none data-[closed]:scale-95 data-[closed]:transform data-[closed]:opacity-0 data-[enter]:duration-100 data-[leave]:duration-75 data-[enter]:ease-out data-[leave]:ease-in"
                            >
                              <div className="py-1">
                                {listItens.map(item => (
                                  <MenuItem key={item}>
                                    <a
                                      className="block px-4 py-2 text-sm text-gray-700 data-[focus]:bg-gray-100 data-[focus]:text-gray-900"
                                      onClick={() => onSelect(item,'UFV')}
                                    >
                                      {item}
                                    </a>
                                  </MenuItem>
                                ))}
                              </div>
                            </MenuItems>
                   </Menu>
                </div>
    );
}

export default DropDonw;