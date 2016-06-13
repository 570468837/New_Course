<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="courses">
		<!-- TODO: Auto-generated template -->
		<xsl:apply-templates></xsl:apply-templates>
		<courses>
		<xsl:for-each select="course">
			<course>
				<�γ̱��>
					<xsl:value-of select="id" />
				</�γ̱��>
				<�γ����>
					<xsl:value-of select="name"></xsl:value-of>
				</�γ����>
				<ѧ��>
					<xsl:value-of select="score"></xsl:value-of>
				</ѧ��>
				<�ڿ���ʦ>
					<xsl:value-of select="teacher" />
				</�ڿ���ʦ>
				<�ڿεص�>
					<xsl:value-of select="location"/>	
				</�ڿ���ʦ>
			</course>	
		</xsl:for-each>
		</courses>
	</xsl:template>
</xsl:stylesheet>