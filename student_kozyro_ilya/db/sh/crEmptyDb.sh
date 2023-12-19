#!/bin/bash
clear
DB_SCOPE="dev"
DB_PREFIX="travel_insurance"
DB_USER_ROLE="${DB_SCOPE}_${DB_PREFIX}_admin"
DB_PASSWORD=$(cat /proc/sys/kernel/random/uuid | sed 's/[-]//g' | head -c 25) 2>&1
DB_NAME="${DB_SCOPE}_${DB_PREFIX}_db"
DB_SCHEMA="${DB_SCOPE}_${DB_PREFIX}_schema"
echo "enter your sudo password (not for postgresql user):"
read -s SUDO_PASS
DB_HOST_NAME="localhost"
DB_PORT="5432"

echo $SUDO_PASS >~/.secret

#psql -c "initdb --locale en_US.UTF-8 -D '/var/lib/postgres/data'"
#sudo /usr/pgsql-14/bin/postgresql-14-setup initdb

cat ~/.secret | sudo -S -u postgres psql -c "DROP SCHEMA IF EXISTS $DB_SCHEMA CASCADE" || exit 1
cat ~/.secret | sudo -S -u postgres psql -c "DROP DATABASE IF EXISTS $DB_NAME" || exit 1
cat ~/.secret | sudo -S -u postgres psql -c "DROP ROLE IF EXISTS $DB_USER_ROLE" || exit 1
#exit
cat ~/.secret | sudo -S -u postgres psql -c "CREATE ROLE $DB_USER_ROLE LOGIN PASSWORD '$DB_PASSWORD'" || exit 1
cat ~/.secret | sudo -S -u postgres psql -c "GRANT $DB_USER_ROLE to postgres" || exit 1
cat ~/.secret | sudo -S -u postgres psql -c "CREATE DATABASE $DB_NAME OWNER $DB_USER_ROLE" || exit 1
#psql -c "ALTER USER $DB_USER_ROLE WITH PASSWORD '$DB_PASSWORD'"
cat ~/.secret | sudo -S -u postgres psql -c "CREATE SCHEMA $DB_SCHEMA " || exit 1
cat ~/.secret | sudo -S -u postgres psql -c "ALTER SCHEMA $DB_SCHEMA OWNER TO $DB_USER_ROLE" || exit 1
cat ~/.secret | sudo -S -u postgres psql -c "ALTER USER $DB_USER_ROLE SET search_path TO $DB_SCHEMA" || exit 1
#cat ~/.secret | sudo -S -u postgres psql -c "GRANT USAGE ON SCHEMA $DB_SCHEMA TO $DB_USER_ROLE"
#cat ~/.secret | sudo -S -u postgres psql -c "GRANT CREATE ON SCHEMA $DB_SCHEMA TO $DB_USER_ROLE"
#cat ~/.secret | sudo -S -u postgres psql -c "GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA $DB_SCHEMA  TO $DB_USER_ROLE"
cat ~/.secret | sudo -S -u postgres psql -c "ALTER DATABASE $DB_NAME SET search_path TO $DB_SCHEMA" || exit 1
cat ~/.secret | sudo -S -u postgres psql -c "SET search_path TO $DB_SCHEMA" || exit 1

rm ~/.secret

DB_URL="jdbc:postgresql://$DB_HOST_NAME:$DB_PORT/$DB_NAME?user=$DB_USER_ROLE&password=$DB_PASSWORD"
echo $DB_URL >~/.lastDbUrl

DB_R2DBC_URL="r2dbc:postgresql://$DB_HOST_NAME:$DB_PORT/$DB_NAME"
echo $DB_R2DBC_URL >~/.$DB_NAME

echo "===================================================="
echo "$DB_URL"
echo "$DB_R2DBC_URL"
echo "===================================================="
echo "CREATE SCHEMA $DB_SCHEMA;"
echo "ALTER DATABASE $DB_NAME SET search_path TO $DB_SCHEMA;"
echo "SET search_path TO $DB_SCHEMA;"
echo "===================================================="