import { Injectable } from '@nestjs/common';
import { Client, Prisma } from '@prisma/client';
import ClientRepository from 'src/repositories/client.repository';

@Injectable()
export default class ClientService {
  constructor(private readonly clientRepository: ClientRepository) {}

  async findAll(): Promise<Client[]> {
    return this.clientRepository.findAll();
  }

  async findById(id: number): Promise<Client | null> {
    return this.clientRepository.findById(id);
  }

  async create(data: Prisma.ClientCreateInput): Promise<Client> {
    return this.clientRepository.create(data);
  }

  async update(id: number, data: Prisma.ClientUpdateInput): Promise<Client> {
    return this.clientRepository.update(id, data);
  }

  async delete(id: number): Promise<void> {
    await this.clientRepository.delete(id);
  }
}
