/*
  Warnings:

  - You are about to drop the column `companyId` on the `client` table. All the data in the column will be lost.
  - You are about to drop the column `email` on the `client` table. All the data in the column will be lost.
  - You are about to drop the column `name` on the `client` table. All the data in the column will be lost.
  - You are about to drop the column `name` on the `user` table. All the data in the column will be lost.
  - You are about to drop the column `role` on the `user` table. All the data in the column will be lost.
  - You are about to drop the `company` table. If the table is not empty, all the data it contains will be lost.
  - You are about to drop the `paymentmethod` table. If the table is not empty, all the data it contains will be lost.
  - You are about to drop the `product` table. If the table is not empty, all the data it contains will be lost.
  - You are about to drop the `seller` table. If the table is not empty, all the data it contains will be lost.
  - You are about to drop the `service` table. If the table is not empty, all the data it contains will be lost.
  - You are about to drop the `supplier` table. If the table is not empty, all the data it contains will be lost.
  - You are about to drop the `transporter` table. If the table is not empty, all the data it contains will be lost.
  - Added the required column `revenda` to the `Client` table without a default value. This is not possible if the table is not empty.

*/
-- DropForeignKey
ALTER TABLE `client` DROP FOREIGN KEY `Client_companyId_fkey`;

-- DropForeignKey
ALTER TABLE `product` DROP FOREIGN KEY `Product_sellerId_fkey`;

-- DropForeignKey
ALTER TABLE `service` DROP FOREIGN KEY `Service_sellerId_fkey`;

-- DropIndex
DROP INDEX `Client_email_key` ON `client`;

-- AlterTable
ALTER TABLE `client` DROP COLUMN `companyId`,
    DROP COLUMN `email`,
    DROP COLUMN `name`,
    ADD COLUMN `revenda` BOOLEAN NOT NULL;

-- AlterTable
ALTER TABLE `user` DROP COLUMN `name`,
    DROP COLUMN `role`,
    ADD COLUMN `cargo` ENUM('USUARIO', 'ADMIN', 'MODERADOR') NOT NULL DEFAULT 'USUARIO',
    ADD COLUMN `nome` VARCHAR(191) NULL;

-- DropTable
DROP TABLE `company`;

-- DropTable
DROP TABLE `paymentmethod`;

-- DropTable
DROP TABLE `product`;

-- DropTable
DROP TABLE `seller`;

-- DropTable
DROP TABLE `service`;

-- DropTable
DROP TABLE `supplier`;

-- DropTable
DROP TABLE `transporter`;

-- CreateTable
CREATE TABLE `Register` (
    `id` INTEGER NOT NULL AUTO_INCREMENT,
    `cpf_cnpj` VARCHAR(191) NOT NULL,
    `nomeRazao` VARCHAR(191) NOT NULL,
    `fantasia` VARCHAR(191) NULL,
    `rg_inscEstad` VARCHAR(191) NOT NULL,
    `inscEstad_inscMuni` VARCHAR(191) NOT NULL,
    `contato` VARCHAR(191) NULL,
    `cep` VARCHAR(191) NOT NULL,
    `logradouro` VARCHAR(191) NOT NULL,
    `numero` VARCHAR(191) NOT NULL,
    `bairro` VARCHAR(191) NOT NULL,
    `complemento` VARCHAR(191) NULL,
    `uf` VARCHAR(191) NOT NULL,
    `municipio` VARCHAR(191) NOT NULL,
    `telefone` VARCHAR(191) NULL,
    `celular` VARCHAR(191) NULL,
    `email` VARCHAR(191) NOT NULL,
    `observacao` VARCHAR(191) NULL,
    `active` BOOLEAN NOT NULL,

    UNIQUE INDEX `Register_email_key`(`email`),
    PRIMARY KEY (`id`)
) DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- CreateTable
CREATE TABLE `Suplliers` (
    `id` INTEGER NOT NULL AUTO_INCREMENT,

    PRIMARY KEY (`id`)
) DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- CreateTable
CREATE TABLE `Transporters` (
    `id` INTEGER NOT NULL AUTO_INCREMENT,

    PRIMARY KEY (`id`)
) DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- CreateTable
CREATE TABLE `Vehicles` (
    `id` INTEGER NOT NULL AUTO_INCREMENT,
    `placa` VARCHAR(191) NOT NULL,
    `uf` VARCHAR(191) NOT NULL,
    `antt` VARCHAR(191) NOT NULL,

    PRIMARY KEY (`id`)
) DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- CreateTable
CREATE TABLE `Sellers` (
    `id` INTEGER NOT NULL AUTO_INCREMENT,
    `nasicmento` DATE NOT NULL,
    `desconto` DOUBLE NOT NULL DEFAULT 0.00,
    `comissao` DOUBLE NOT NULL DEFAULT 0.00,
    `tipoComissao` ENUM('parcelaRecebida', 'totalVenda') NOT NULL,
    `baseCalculo` ENUM('totalLiquido', 'totalBruto', 'total') NOT NULL,

    PRIMARY KEY (`id`)
) DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- AddForeignKey
ALTER TABLE `Client` ADD CONSTRAINT `Client_id_fkey` FOREIGN KEY (`id`) REFERENCES `Register`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE `Suplliers` ADD CONSTRAINT `Suplliers_id_fkey` FOREIGN KEY (`id`) REFERENCES `Register`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE `Transporters` ADD CONSTRAINT `Transporters_id_fkey` FOREIGN KEY (`id`) REFERENCES `Register`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE `Vehicles` ADD CONSTRAINT `Vehicles_id_fkey` FOREIGN KEY (`id`) REFERENCES `Transporters`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE `Sellers` ADD CONSTRAINT `Sellers_id_fkey` FOREIGN KEY (`id`) REFERENCES `Register`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE;
