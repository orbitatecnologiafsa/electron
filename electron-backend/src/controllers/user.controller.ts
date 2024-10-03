import { Controller, Get, Post, Put, Delete, Body, Param, Injectable } from '@nestjs/common';
import { UserService } from '../services/user.service';
import { User } from '@prisma/client';

@Controller('users')
export class UserController {
  constructor(private readonly userService: UserService) {}

  @Post()
  async createUser(@Body() data: Omit<User, 'id'>): Promise<User> {
    return this.userService.createUser(data);
  }

  @Get(':id')
  async getUserById(@Param('id') id: string): Promise<User | null> {
    return this.userService.getUserById(Number(id));
  }

  @Get()
  async getAllUsers(): Promise<User[]> {
    return this.userService.getAllUsers();
  }

  @Put(':id')
  async updateUser(@Param('id') id: string, @Body() data: Partial<User>): Promise<User> {
    return this.userService.updateUser(Number(id), data);
  }

  @Delete(':id')
  async deleteUser(@Param('id') id: string): Promise<User> {
    return this.userService.deleteUser(Number(id));
  }
}
