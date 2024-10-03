import { Client, Prisma } from '@prisma/client';
import { PrismaService } from 'src/database/prisma.service';

export default class ClientRepository {
  constructor(private readonly prisma: PrismaService) {}  

  async findAll(): Promise<Client[]> {
    return this.prisma.client.findMany();
  }

  async findById(id: number): Promise<Client | null> {
    return this.prisma.client.findUnique({ where: { id } });
  }

  async create(data: Prisma.ClientCreateInput): Promise<Client> {
    return this.prisma.client.create({ data });
  }

  async update(id: number, data: Prisma.ClientUpdateInput): Promise<Client> {
    return this.prisma.client.update({ where: { id }, data });
  }

  async delete(id: number): Promise<void> {
    await this.prisma.client.delete({ where: { id } });
  }

}
