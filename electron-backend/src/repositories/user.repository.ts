import { Injectable } from '@nestjs/common';
import { PrismaService } from '../database/prisma.service';
import { User } from '@prisma/client';

@Injectable()
export class UserRepository {
  constructor(private prisma: PrismaService) {}

  // Create
  async create(data: Omit<User, 'id'>): Promise<User> {
    return this.prisma.user.create({ data });
  }

  // Read
  async findById(id: number): Promise<User | null> {
    return this.prisma.user.findUnique({ where: { id } });
  }

  async findAll(): Promise<User[]> {
    return this.prisma.user.findMany();
  }

  // Update
  async update(id: number, data: Partial<User>): Promise<User> {
    return this.prisma.user.update({
      where: { id },
      data,
    });
  }

  // Delete
  async delete(id: number): Promise<User> {
    return this.prisma.user.delete({ where: { id } });
  }
}