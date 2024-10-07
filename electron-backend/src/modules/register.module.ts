import { Module } from '@nestjs/common';
import RegisterController from 'src/controllers/register.controller';
import { PrismaService } from 'src/database/prisma.service';
import RegisterRepository from 'src/repositories/register.repository';
import RegisterService from 'src/services/register.service';

@Module({
  controllers: [RegisterController],
  providers: [RegisterService, RegisterRepository, PrismaService],
  exports: [RegisterService],
})
export default class RegisterModule {}
