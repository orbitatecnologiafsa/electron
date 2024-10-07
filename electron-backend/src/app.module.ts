import { Module } from '@nestjs/common';
import { PrismaService } from './database/prisma.service';
import ClientModule from './modules/client.module';
import { UserModule } from './modules/user.module';
import RegisterModule from './modules/register.module';

@Module({
  imports: [UserModule, ClientModule, RegisterModule],
  controllers: [],
  providers: [PrismaService],
})
export class AppModule {}
