# Generates Java Protobuf code and places code in a location expected by the project.
# Requires both protoc and protoc-gen-java-grpc

protoc --proto_path=$PSScriptRoot KeyValueService.proto --java_out=$PSScriptRoot --java-grpc_out=$PSScriptRoot
if ($?) {
	Write-Host "Moving generated code from protobuf/generated to generated"
	Remove-Item $PSScriptRoot\generated -Recurse
	Move-Item -Path $PSScriptRoot\protobuf\generated -Destination $PSScriptRoot
	Remove-Item $PSScriptRoot\protobuf -Recurse
}
