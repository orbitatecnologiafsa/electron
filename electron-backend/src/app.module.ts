import { Module } from '@nestjs/common';
import { PrismaService } from './database/prisma.service';
import { UserModule } from './modules/user.module';
import { CompanyModule } from './modules/company.module';
import PaymentMethodModule from './modules/payment.method.module';

@Module({
  imports: [UserModule, CompanyModule, PaymentMethodModule],
  controllers: [],
  providers: [PrismaService],
})
export class AppModule {}
