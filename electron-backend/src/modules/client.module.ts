import { Module } from '@nestjs/common';
import ClientController from 'src/controllers/client.controller';
import { PrismaService } from 'src/database/prisma.service';
import ClientRepository from 'src/repositories/client.repository';
import ClientService from 'src/services/client.service';

@Module({
  controllers: [ClientController],  
  providers: [ClientService, ClientRepository, PrismaService],
  exports: [ClientService],
})
export default class ClientModule {}
