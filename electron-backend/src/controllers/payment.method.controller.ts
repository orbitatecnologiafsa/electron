import { Controller, Param, Put, Get, Post, Delete, Body } from '@nestjs/common';
import PaymentMethodService from 'src/services/payment.method.service';
import { PaymentMethod } from '@prisma/client';

@Controller('payment-methods')
export default class PaymentMethodController {
  constructor(private readonly paymentMethodService: PaymentMethodService) {}

  @Get()
  async findAll(): Promise<PaymentMethod[]> {
    return this.paymentMethodService.findAll();
  }

  @Get(':id')
  async findOne(@Param('id') id: number): Promise<PaymentMethod> {
    return this.paymentMethodService.findById(id);
  }

  @Post()
  async create(@Body() data: Omit<PaymentMethod, 'id'>): Promise<PaymentMethod> {
    return this.paymentMethodService.create(data);
  }

  @Put(':id')
  async update(@Param('id') id: number, @Body() data: PaymentMethod): Promise<PaymentMethod> {
    return this.paymentMethodService.update(id, data);
  }

  @Delete(':id')
  async delete(@Param('id') id: number): Promise<void> {
    this.paymentMethodService.delete(id);
  }
}
