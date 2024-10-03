import { Injectable } from '@nestjs/common';
import PaymentMethodRepository from 'src/repositories/payment.method.repository';
import { PaymentMethod } from '@prisma/client';

@Injectable()
export default class PaymentMethodService {
  constructor(
    private readonly paymentMethodRepository: PaymentMethodRepository,
  ) {}

  async findAll(): Promise<PaymentMethod[]> {
    return this.paymentMethodRepository.findAll();
  }

  async findById(id: number): Promise<PaymentMethod | null> {
    return this.paymentMethodRepository.findById(id);
  }

  async create(paymentMethod: Partial<PaymentMethod>): Promise<PaymentMethod> {
    return this.paymentMethodRepository.create(paymentMethod as PaymentMethod);
  }

  async update(
    id: number,
    paymentMethod: Partial<PaymentMethod>,
  ): Promise<PaymentMethod | null> {
    return this.paymentMethodRepository.update(id, paymentMethod as { id: number; method: string });
  }

  async delete(id: number): Promise<void> {
    this.paymentMethodRepository.delete(id);
  }
}
