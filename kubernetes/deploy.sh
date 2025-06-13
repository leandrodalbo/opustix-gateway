#!/bin/bash

set -e  
NAMESPACE=eventsgateway
SERVICE_NAME=event-gateway

echo "Creating namespace: $NAMESPACE"
kubectl create namespace $NAMESPACE --dry-run=client -o yaml | kubectl apply -f -

echo "Applying deployment..."
kubectl apply -f ./yaml/deployment.yaml -n $NAMESPACE

echo "Applying service..."
kubectl apply -f ./yaml/service.yaml -n $NAMESPACE

echo "Done."
