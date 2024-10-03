import { Injectable } from '@nestjs/common';
import { Company } from '@prisma/client';
import { PrismaService } from 'src/database/prisma.service';

@Injectable()
export default class CompanyRepository {
  constructor(private prisma: PrismaService) {}

  async create(data: Omit<Company, 'id'>): Promise<Company> {
    return this.prisma.company.create({ data });
  }

  // Read
  async findById(id: number): Promise<Company | null> {
    return this.prisma.company.findUnique({ where: { id } });
  }

  async findAll(): Promise<Company[]> {
    return this.prisma.company.findMany();
  }

  // Update
  async update(id: number, data: Partial<Company>): Promise<Company> {
    return this.prisma.company.update({
      where: { id },
      data,
    });
  }

  // Delete
  async delete(id: number): Promise<Company> {
    return this.prisma.company.delete({ where: { id } });
  }
}
