import { Injectable } from '@nestjs/common';
import { PaymentMethod } from '@prisma/client';
import { PrismaService } from 'src/database/prisma.service';

@Injectable()
export default class PaymentMethodRepository {
  constructor(private readonly prisma: PrismaService) {}

  async findAll(): Promise<PaymentMethod[]> {
    return this.prisma.paymentMethod.findMany();
  }

  async findById(id: number): Promise<PaymentMethod | null> {
    return this.prisma.paymentMethod.findUnique({ where: { id } });
  }

  async create(data: PaymentMethod): Promise<PaymentMethod> {
    return this.prisma.paymentMethod.create({ data });
  }

  async update(id: number, data: PaymentMethod): Promise<PaymentMethod | null> {
    return this.prisma.paymentMethod.update({ where: { id }, data });
  } 

  async delete(id: number): Promise<void> {
    await this.prisma.paymentMethod.delete({ where: { id } });
  }
}
