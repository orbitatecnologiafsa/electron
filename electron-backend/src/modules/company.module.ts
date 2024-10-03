import { Module } from '@nestjs/common';
import CompanyController from 'src/controllers/company.controller';
import CompanyService from 'src/services/company.service';
import CompanyRepository from 'src/repositories/company.repository';
import { PrismaService } from 'src/database/prisma.service';

@Module({
  controllers: [CompanyController],
  providers: [CompanyService, CompanyRepository, PrismaService],
  exports: [CompanyService],
})
export class CompanyModule {}
