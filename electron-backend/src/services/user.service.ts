import { Injectable } from '@nestjs/common';
import {UserRepository} from '../repositories/user.repository';
import { User } from '@prisma/client';

@Injectable()
export class UserService {
  constructor(private userRepository: UserRepository) {}

  async createUser(data: Omit<User, 'id'>): Promise<User> {
    return this.userRepository.create(data);
  }

  async getUserById(id: number): Promise<User | null> {
    return this.userRepository.findById(id);
  }

  async getAllUsers(): Promise<User[]> {
    return this.userRepository.findAll();
  }

  async updateUser(id: number, data: Partial<User>): Promise<User> {
    return this.userRepository.update(id, data);
  }

  async deleteUser(id: number): Promise<User> {
    return this.userRepository.delete(id);
  }
}