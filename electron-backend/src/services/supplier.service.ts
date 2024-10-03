import { Injectable } from '@nestjs/common';
import { Supplier } from '@prisma/client';
import SupplierRepository from 'src/repositories/supplier.repository';

@Injectable()
export default class SupplierService {
  constructor(private readonly supplierRepository: SupplierRepository) {} 

  async findAll(): Promise<Supplier[]> {
    return this.supplierRepository.findAll();
  }

  async findById(id: number): Promise<Supplier | null> {
    return this.supplierRepository.findById(id);
  }

  async create(data: Omit<Supplier, 'id'>): Promise<Supplier> {
    return this.supplierRepository.create(data);
  }

  async update(id: number, data: Omit<Supplier, 'id'>): Promise<Supplier> {
    return this.supplierRepository.update(id, data);
  }

  async delete(id: number): Promise<void> { 
    await this.supplierRepository.delete(id);
  }

}
