import { Body, Controller, Delete, Get, Param, Post, Put } from "@nestjs/common";
import { Register } from "@prisma/client";
import RegisterService from "src/services/register.service";

@Controller('registers')
export default class RegisterController{
  constructor(private readonly registerService: RegisterService){}

  @Get()
  async findAll(): Promise<Register[]> {
    return this.registerService.findAll();
  }

  @Get(':id')
  async findOne(@Param('id') id: number): Promise<Register> {
    return this.registerService.findById(id);
  }

  @Post()
  async create(@Body() data: Omit<Register, 'id'>): Promise<Register> {
    return this.registerService.create(data);
  }

  @Put(':id')
  async update(@Param('id') id: number, @Body() data: Register): Promise<Register> {
    return this.registerService.update(id, data);
  }

  @Delete(':id')
  async delete(@Param('id') id: number): Promise<void> {
    this.registerService.delete(id);
  }

}