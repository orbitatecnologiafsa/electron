import {
  Controller,
  Post,
  Body,
  Get,
  Param,
  Delete,
  Put,
} from '@nestjs/common';
import { Company } from '@prisma/client';
import CompanyService from 'src/services/company.service';

@Controller('companies')
export default class CompanyController {
  constructor(private companyService: CompanyService) {}

  @Post()
  async create(@Body() data: Omit<Company, 'id'>): Promise<Company> {
    return this.companyService.create(data);
  }

  @Get(':id')
  async findById(@Param('id') id: number): Promise<Company | null> {
    return this.companyService.findById(id);
  }

  @Get()
  async findAll(): Promise<Company[]> {
    return this.companyService.findAll();
  }

  @Put(':id')
  async update(
    @Param('id') id: number,
    @Body() data: Partial<Company>,
  ): Promise<Company> {
    return this.companyService.update(id, data);
  }

  @Delete(':id')
  async delete(@Param('id') id: number): Promise<Company> {
    return this.companyService.delete(id);
  }
}
