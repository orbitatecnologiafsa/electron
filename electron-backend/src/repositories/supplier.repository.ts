import { Injectable } from '@nestjs/common';
import { Supplier } from '@prisma/client';
import { PrismaService } from 'src/database/prisma.service';

@Injectable()
export default class SupplierRepository {
  constructor(private readonly prisma: PrismaService) {}  

  async findAll(): Promise<Supplier[]> {
    return this.prisma.supplier.findMany();
  }

  async findById(id: number): Promise<Supplier | null> {
    return this.prisma.supplier.findUnique({ where: { id } });
  }

  async create(data: Omit<Supplier, 'id'>): Promise<Supplier> {
    return this.prisma.supplier.create({ data });
  }

  async update(id: number, data: Omit<Supplier, 'id'>): Promise<Supplier> {
    return this.prisma.supplier.update({ where: { id }, data });
  }

  async delete(id: number): Promise<void> {
    await this.prisma.supplier.delete({ where: { id } });
  }
}
