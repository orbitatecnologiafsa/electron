import { Module } from '@nestjs/common';
import PaymentMethodController from '../controllers/payment.method.controller';
import PaymentMethodRepository from 'src/repositories/payment.method.repository';
import PaymentMethodService from 'src/services/payment.method.service';
import { PrismaService } from 'src/database/prisma.service';

@Module({
  controllers: [PaymentMethodController],
  providers: [PaymentMethodService, PaymentMethodRepository, PrismaService],
  exports: [PaymentMethodService],
})
export default class PaymentMethodModule {}

