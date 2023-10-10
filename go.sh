time docker build . \
  --build-arg UID=4567 \
  --build-arg USERNAME="tc_pst03" \
  --build-arg GID="1137" \
  --build-arg GROUP="pst_pub" \
  --tag "dockerhub.ebi.ac.uk/biomodels/services:0.1-omex-dev"