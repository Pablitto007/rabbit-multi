FROM rabbitmq

RUN rabbitmq-plugins enable --offline rabbitmq_management
RUN rabbitmq-plugins enable --offline rabbitmq_shovel
RUN rabbitmq-plugins enable --offline rabbitmq_shovel_management

ENV RABBITMQ_LOG_BASE /var/log/rabbitmq
ENV RABBITMQ_LOGS $RABBITMQ_LOG_BASE/multi-rabbitmq.log

VOLUME ["/var/lib/rabbitmq","/var/log/rabbitmq"]

EXPOSE 15671 15672 5672