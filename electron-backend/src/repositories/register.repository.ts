import { Injectable } from "@nestjs/common";
import { Prisma, Register } from "@prisma/client";
import { PrismaService } from "src/database/prisma.service";

@Injectable()
export default class RegisterRepository {
  constructor(private readonly prisma:PrismaService){}

  async findAll(): Promise<Register[]> {
    return this.prisma.register.findMany();
  }  

  async findById(id: number): Promise<Register | null> {
    return this.prisma.register.findUnique({where: {id}});
  }

  async create(data: Prisma.RegisterCreateInput): Promise<Register> {
    return this.prisma.register.create({ data });
  }

  async update(id: number, data: Prisma.RegisterUpdateInput): Promise<Register> {
    return this.prisma.register.update({ where: { id }, data });
  }

  async delete(id: number): Promise<void> {
    await this.prisma.register.delete({ where: { id } });
  }

}