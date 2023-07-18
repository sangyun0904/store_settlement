terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 4.16"
    }
  }

  required_version = ">= 1.2.0"
}

provider "aws" {
  region  = "ap-northeast-2"
}

# resource "<provider>_resourceType" "name" {
#     config options ...
#     key1 = "value"
#     key2 = "another value"
# }

resource "aws_vpc" "Store-settlement-vpc" {
  cidr_block = "10.0.0.0/16"

  tags = {
    Name = "Store-settlement VPC"
  }
}

resource "aws_subnet" "public_subnets" {
  count             = length(var.public_subnet_cidrs)
  vpc_id            = aws_vpc.Store-settlement-vpc.id
  cidr_block        = element(var.public_subnet_cidrs, count.index)
  availability_zone = element(var.azs, count.index)

  tags = {
    Name = "Public Subnet ${count.index + 1}"
  }
}

resource "aws_subnet" "private_subnets" {
  count             = length(var.private_subnet_cidrs)
  vpc_id            = aws_vpc.Store-settlement-vpc.id
  cidr_block        = element(var.private_subnet_cidrs, count.index)
  availability_zone = element(var.azs, count.index)

  tags = {
    Name = "Private Subnet ${count.index + 1}"
  }
}

resource "aws_internet_gateway" "Store-settlement-gw" {
    vpc_id = aws_vpc.Store-settlement-vpc.id 

    tags = {
        Name = "Store-settlement VPC IG"
    }
}

resource "aws_route_table" "Store-settlement-second-rt" {
 vpc_id = aws_vpc.Store-settlement-vpc.id
 
 route {
   cidr_block = "0.0.0.0/0"
   gateway_id = aws_internet_gateway.Store-settlement-gw.id
 }
 
 tags = {
   Name = "Store-settlement 2nd Route Table"
 }
}

resource "aws_route_table_association" "public_subnet_asso" {
 count          = length(var.public_subnet_cidrs)
 subnet_id      = element(aws_subnet.public_subnets[*].id, count.index)
 route_table_id = aws_route_table.Store-settlement-second-rt.id
}

resource "aws_instance" "Store-settlement-app-server" {
  ami           = "ami-0a306845f8cfbd41a"
  instance_type = "t2.micro"
  subnet_id     = aws_subnet.private_subnets[0].id

  tags = {
    Name = "Store-settlement AppServer Instance"
  }
}
