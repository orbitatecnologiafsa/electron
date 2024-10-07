import {
  Controller,
  Get,
  Post,
  Put,
  Delete,
  Body,
  Param,
} from '@nestjs/common';
import { Client } from '@prisma/client';
import ClientService from 'src/services/client.service';

@Controller('clients')
export default class ClientController {
  constructor(private readonly clientService: ClientService) {}

  @Get()
  async findAll(): Promise<Client[]> {
    return this.clientService.findAll();
  }

  @Get(':id')
  async findOne(@Param('id') id: number): Promise<Client> {
    return this.clientService.findById(id);
  }

  @Post()
  async create(@Body() data: Omit<Client, 'id'>): Promise<Client> {
    return this.clientService.create(data);
  }

  @Put(':id')
  async update(@Param('id') id: number, @Body() data: Client): Promise<Client> {
    return this.clientService.update(id, data);
  }

  @Delete(':id')
  async delete(@Param('id') id: number): Promise<void> {
    this.clientService.delete(id);
  }
}
