<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" encoding="UTF-8" />
	<xsl:template match="course">
		<!-- TODO: Auto-generated template -->
		<xsl:apply-templates />
		<courses>
			<xsl:for-each select="course">
				<course>
					<id>
						<xsl:value-of select="�γ̱��"></xsl:value-of>
					</id>
					<name>
						<xsl:value-of select="�γ�����" />
					</name>
					<score>
						<xsl:value-of select="ѧ��"/>
					</score>
					<teacher>
						<xsl:value-of select="�ڿ���ʦ" />
					</teacher>
					<location>
						<xsl:value-of select="�ڿεص�"/>
					</location>
				</course>
			</xsl:for-each>
		</courses>
	</xsl:template>
</xsl:stylesheet>