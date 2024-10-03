import { Injectable } from '@nestjs/common';
import CompanyRepository from '../repositories/company.repository';
import { Company } from '@prisma/client';

@Injectable()
export default class CompanyService {
  constructor(private companyRepository: CompanyRepository) {}

  async create(data: Omit<Company, 'id'>): Promise<Company> {
    return this.companyRepository.create(data);
  }

  async findById(id: number): Promise<Company | null> {
    return this.companyRepository.findById(id);
  }

  async findAll(): Promise<Company[]> {
    return this.companyRepository.findAll();
  }

  async update(id: number, data: Partial<Company>): Promise<Company> {
    return this.companyRepository.update(id, data);
  }

  async delete(id: number): Promise<Company> {
    return this.companyRepository.delete(id);
  }
}
