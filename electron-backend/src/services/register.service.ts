import { Injectable } from '@nestjs/common';
import { Prisma, Register } from '@prisma/client';
import RegisterRepository from 'src/repositories/register.repository';

@Injectable()
export default class RegisterService {
  constructor(private readonly registerRepository: RegisterRepository) {}

  async findAll(): Promise<Register[]> {
    return this.registerRepository.findAll();
  }

  async findById(id: number): Promise<Register | null> {
    return this.registerRepository.findById(id);
  }

  async create(data: Prisma.RegisterCreateInput): Promise<Register> {
    return this.registerRepository.create(data);
  }

  async update(
    id: number,
    data: Prisma.RegisterUpdateInput,
  ): Promise<Register> {
    return this.registerRepository.update(id, data);
  }

  async delete(id: number): Promise<void> {
    await this.registerRepository.delete(id);
  }
}
